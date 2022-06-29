package net.rubencm.forum.topicsservice.application.eventlisteners;

import net.rubencm.forum.shared.domain.command.CommandBus;
import net.rubencm.forum.shared.domain.event.DomainEventListener;
import net.rubencm.forum.shared.domain.events.message.MessageCreatedEvent;
import net.rubencm.forum.topicsservice.application.addposttotpic.AddPostToTopicCommand;
import net.rubencm.forum.topicsservice.application.createtopic.CreateTopicCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateOrUpdateTopicOnMessageCreated implements DomainEventListener<MessageCreatedEvent> {

    private final CommandBus commandBus;

    @Autowired
    public CreateOrUpdateTopicOnMessageCreated(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void on(MessageCreatedEvent event) {
        if (event.getAggregateId().equals(event.getTopicId())) { // If message parent if itself, is a topic
            this.commandBus.execute(
                    new CreateTopicCommand(
                            event.getAggregateId(),
                            event.getTitle(),
                            event.getPosterName(),
                            event.getForumId()
                    )
            );
        } else { // Update last post of topic
            this.commandBus.execute(
                    new AddPostToTopicCommand(
                            event.getTopicId(),
                            event.getPosterName()
                    )
            );
        }
    }
}
