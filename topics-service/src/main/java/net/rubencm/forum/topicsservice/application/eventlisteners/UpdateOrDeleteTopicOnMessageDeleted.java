package net.rubencm.forum.topicsservice.application.eventlisteners;

import net.rubencm.forum.shared.domain.event.DomainEventListener;
import net.rubencm.forum.shared.domain.event.EventBus;
import net.rubencm.forum.shared.domain.events.message.MessageDeletedEvent;
import net.rubencm.forum.shared.domain.events.topic.TopicDeletedEvent;
import net.rubencm.forum.shared.domain.valueobjects.TopicId;
import net.rubencm.forum.topicsservice.domain.aggregates.Topic;
import net.rubencm.forum.topicsservice.domain.findtopic.FindTopic;
import net.rubencm.forum.topicsservice.domain.repositories.Topics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateOrDeleteTopicOnMessageDeleted implements DomainEventListener<MessageDeletedEvent> {

    final private FindTopic findTopic;
    final private Topics topics;
    final private EventBus eventBus;

    @Autowired
    public UpdateOrDeleteTopicOnMessageDeleted(FindTopic findTopic, Topics topics, EventBus eventBus) {
        this.findTopic = findTopic;
        this.topics = topics;
        this.eventBus = eventBus;
    }

    @Override
    public void on(MessageDeletedEvent event) {
        Topic topic = this.findTopic.execute(new TopicId(event.getTopicId()));

        if (event.getAggregateId().equals(event.getTopicId())) {
            // If the message deleted was a topic, remove the topic here too
            this.removeTopic(topic);
        } else {
            // If the message deleted was a response, decrease topic messages
            this.decreaseTopicMessages(topic);
        }

    }

    private void removeTopic(Topic topic) {
        topics.delete(topic);

        eventBus.publish(new TopicDeletedEvent(topic.getId().value(), topic.getForumId().value()));
    }

    private void decreaseTopicMessages(Topic topic) {
        topic.removeMessage();

        topics.save(topic);
        eventBus.publish(topic.pullDomainEvents());
    }

}
