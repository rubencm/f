package net.rubencm.forum.topicsservice.application.createtopic;

import net.rubencm.forum.shared.domain.command.CommandHandler;
import net.rubencm.forum.shared.domain.event.EventBus;
import net.rubencm.forum.shared.domain.valueobjects.ForumId;
import net.rubencm.forum.shared.domain.valueobjects.TopicId;
import net.rubencm.forum.shared.domain.valueobjects.User;
import net.rubencm.forum.topicsservice.domain.aggregates.Topic;
import net.rubencm.forum.topicsservice.domain.repositories.Topics;
import net.rubencm.forum.topicsservice.domain.valueobjects.TopicTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTopicHandler implements CommandHandler<CreateTopicCommand> {

    final private Topics topics;

    final private EventBus eventBus;

    public CreateTopicHandler(@Autowired Topics topics, @Autowired EventBus eventBus) {
        this.topics = topics;
        this.eventBus = eventBus;
    }

    public void handle(CreateTopicCommand command) {
        Topic topic = Topic.create(
                new TopicId(command.getId()),
                new TopicTitle(command.getTitle()),
                new User(command.getAuthor()),
                new ForumId(command.getForumId())
        );

        topics.save(topic);

        eventBus.publish(topic.pullDomainEvents());
    }

}
