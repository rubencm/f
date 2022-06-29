package net.rubencm.forum.messagesservice.application.createmessage;

import net.rubencm.forum.messagesservice.domain.aggregates.Message;
import net.rubencm.forum.messagesservice.domain.repositories.Messages;
import net.rubencm.forum.messagesservice.domain.valueobjects.MessageBody;
import net.rubencm.forum.messagesservice.domain.valueobjects.MessageTitle;
import net.rubencm.forum.messagesservice.domain.valueobjects.PosterName;
import net.rubencm.forum.shared.domain.command.CommandHandler;
import net.rubencm.forum.shared.domain.event.EventBus;
import net.rubencm.forum.shared.domain.valueobjects.ForumId;
import net.rubencm.forum.shared.domain.valueobjects.MessageId;
import net.rubencm.forum.shared.domain.valueobjects.TopicId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateMessageHandler implements CommandHandler<CreateMessageCommand> {

    final private Messages messages;

    final private EventBus eventBus;

    public CreateMessageHandler(@Autowired Messages messages, @Autowired EventBus eventBus) {
        this.messages = messages;
        this.eventBus = eventBus;
    }

    public void handle(CreateMessageCommand command) {
        Message message = Message.create(
                new MessageId(command.getId()),
                new MessageTitle(command.getMessageTitle()),
                new MessageBody(command.getMessageBody()),
                new PosterName(command.getPosterName()),
                new TopicId(command.getTopicId()),
                new ForumId(command.getForumId())
        );

        messages.save(message);

        eventBus.publish(message.pullDomainEvents());
    }

}
