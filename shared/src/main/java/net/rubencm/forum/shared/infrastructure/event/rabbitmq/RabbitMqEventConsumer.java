package net.rubencm.forum.shared.infrastructure.event.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import net.rubencm.forum.shared.domain.event.DomainEvent;
import net.rubencm.forum.shared.domain.event.DomainEventListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RabbitMqEventConsumer implements CommandLineRunner {

    private static final String EXCHANGE_NAME = "forums_exchange";
    private final ApplicationContext context;
    private final RabbitTemplate rabbitTemplate;
    private final RabbitAdmin rabbitAdmin;
    private final DomainEventRegistry domainEventListenerRegistry;

    @Autowired
    public RabbitMqEventConsumer(ApplicationContext context, RabbitTemplate rabbitTemplate, DomainEventRegistry domainEventListenerRegistry) {
        this.context = context;
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitAdmin = new RabbitAdmin(rabbitTemplate);
        this.domainEventListenerRegistry = domainEventListenerRegistry;
    }

    @Override
    public void run(String... args) throws Exception {
        // Exchange
        TopicExchange exchange = new TopicExchange(EXCHANGE_NAME, true, false);
        this.rabbitAdmin.declareExchange(exchange);

        // Declare a queue for each event this service consume, with routingKey=eventName
        Map<String, String> eventQueueMap = new HashMap<>();
        for (String eventName : this.domainEventListenerRegistry.getEvents()) {
            Queue queue = new Queue(eventName);
            this.rabbitAdmin.declareQueue(queue);

            eventQueueMap.put(eventName, queue.getActualName());

            Binding binding = BindingBuilder.bind(queue).to(exchange).with(eventName);
            this.rabbitAdmin.declareBinding(binding);
        }

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            DomainEvent domainEvent = DomainEvent.deserialize(delivery.getBody());

            // Execute listeners for this event
            for (Class<? extends DomainEventListener> listenerClass : this.domainEventListenerRegistry.getListeners(domainEvent.eventName())) {
                DomainEventListener listener = context.getBean(listenerClass);

                // Run listener
                listener.on(domainEvent);
            }
        };

        // For each event register a callback
        Channel channel = rabbitTemplate.getConnectionFactory().createConnection().createChannel(false);
        for (String eventName : this.domainEventListenerRegistry.getEvents()) {
            channel.basicConsume(eventQueueMap.get(eventName), true, deliverCallback, consumerTag -> {
            });
        }
    }
}
