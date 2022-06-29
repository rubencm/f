package net.rubencm.forum.shared.infrastructure.event.rabbitmq;

import net.rubencm.forum.shared.domain.event.DomainEvent;
import net.rubencm.forum.shared.domain.event.EventBus;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitMqEventBusImpl implements EventBus {

    private static final String EXCHANGE_NAME = "forums_exchange";
    private final RabbitTemplate rabbitTemplate;

    public RabbitMqEventBusImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate);
        rabbitAdmin.declareExchange(new TopicExchange(EXCHANGE_NAME, true, false));
    }

    @Override
    public void publish(DomainEvent event) {
        String routingKey = event.eventName();
        Message message = MessageBuilder.withBody(event.serialize()).build();
        this.rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, message);
    }

    @Override
    public void publish(List<DomainEvent> events) {
        for (DomainEvent event : events) {
            this.publish(event);
        }
    }
}
