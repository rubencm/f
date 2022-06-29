package net.rubencm.forum.topicsservice.application.eventlisteners;

import net.rubencm.forum.shared.domain.event.DomainEventListener;
import net.rubencm.forum.shared.domain.event.EventBus;
import net.rubencm.forum.shared.domain.events.message.MessageUpdatedEvent;
import net.rubencm.forum.shared.domain.valueobjects.TopicId;
import net.rubencm.forum.topicsservice.domain.aggregates.Topic;
import net.rubencm.forum.topicsservice.domain.findtopic.FindTopic;
import net.rubencm.forum.topicsservice.domain.findtopic.TopicNotFoundException;
import net.rubencm.forum.topicsservice.domain.repositories.Topics;
import net.rubencm.forum.topicsservice.domain.valueobjects.TopicTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateTopicOnMessageUpdated implements DomainEventListener<MessageUpdatedEvent> {
    final private FindTopic findTopic;
    final private Topics topics;

    final private EventBus eventBus;

    @Autowired
    public UpdateTopicOnMessageUpdated(FindTopic findTopic, Topics topics, EventBus eventBus) {
        this.findTopic = findTopic;
        this.topics = topics;
        this.eventBus = eventBus;
    }

    @Override
    public void on(MessageUpdatedEvent event) {
        try {
            Topic topic = this.findTopic.execute(new TopicId(event.getAggregateId()));

            topic.edit(new TopicTitle(event.getTitle()));

            topics.save(topic);
            eventBus.publish(topic.pullDomainEvents());
        } catch (TopicNotFoundException e) {

        }
    }
}
