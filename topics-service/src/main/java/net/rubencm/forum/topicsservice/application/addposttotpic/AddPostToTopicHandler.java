package net.rubencm.forum.topicsservice.application.addposttotpic;

import net.rubencm.forum.shared.domain.command.CommandHandler;
import net.rubencm.forum.shared.domain.event.EventBus;
import net.rubencm.forum.shared.domain.valueobjects.TopicId;
import net.rubencm.forum.shared.domain.valueobjects.User;
import net.rubencm.forum.topicsservice.domain.aggregates.Topic;
import net.rubencm.forum.topicsservice.domain.findtopic.FindTopic;
import net.rubencm.forum.topicsservice.domain.repositories.Topics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddPostToTopicHandler implements CommandHandler<AddPostToTopicCommand> {
    final private FindTopic findTopic;
    final private Topics topics;

    final private EventBus eventBus;

    public AddPostToTopicHandler(@Autowired FindTopic findTopic, @Autowired Topics topics, @Autowired EventBus eventBus) {
        this.findTopic = findTopic;
        this.topics = topics;
        this.eventBus = eventBus;
    }

    public void handle(AddPostToTopicCommand command) {
        Topic topic = this.findTopic.execute(new TopicId(command.getId()));

        topic.addMessage(new User(command.getPosterName()));

        topics.save(topic);
        eventBus.publish(topic.pullDomainEvents());
    }
}
