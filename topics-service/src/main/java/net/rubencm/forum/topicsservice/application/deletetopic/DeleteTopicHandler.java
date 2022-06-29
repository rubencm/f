package net.rubencm.forum.topicsservice.application.deletetopic;

import net.rubencm.forum.shared.domain.command.CommandHandler;
import net.rubencm.forum.shared.domain.event.EventBus;
import net.rubencm.forum.shared.domain.events.topic.TopicDeletedEvent;
import net.rubencm.forum.shared.domain.valueobjects.TopicId;
import net.rubencm.forum.topicsservice.domain.aggregates.Topic;
import net.rubencm.forum.topicsservice.domain.findtopic.FindTopic;
import net.rubencm.forum.topicsservice.domain.repositories.Topics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteTopicHandler implements CommandHandler<DeleteTopicCommand> {
    final private FindTopic findTopic;
    final private Topics topics;

    final private EventBus eventBus;

    @Autowired
    public DeleteTopicHandler(FindTopic findTopic, Topics topics, EventBus eventBus) {
        this.findTopic = findTopic;
        this.topics = topics;
        this.eventBus = eventBus;
    }

    public void handle(DeleteTopicCommand command) {
        Topic topic = this.findTopic.execute(new TopicId(command.getId()));

        topics.delete(topic);

        eventBus.publish(new TopicDeletedEvent(topic.getId().value(), topic.getForumId().value()));
    }
}
