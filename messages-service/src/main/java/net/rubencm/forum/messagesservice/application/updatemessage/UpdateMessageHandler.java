package net.rubencm.forum.messagesservice.application.updatemessage;

import net.rubencm.forum.messagesservice.domain.aggregates.Message;
import net.rubencm.forum.messagesservice.domain.findmessage.FindMessage;
import net.rubencm.forum.messagesservice.domain.repositories.Messages;
import net.rubencm.forum.messagesservice.domain.valueobjects.MessageBody;
import net.rubencm.forum.messagesservice.domain.valueobjects.MessageTitle;
import net.rubencm.forum.shared.domain.command.CommandHandler;
import net.rubencm.forum.shared.domain.event.EventBus;
import net.rubencm.forum.shared.domain.valueobjects.MessageId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateMessageHandler implements CommandHandler<UpdateMessageCommand> {
    final private FindMessage findMessage;
    final private Messages messages;

    final private EventBus eventBus;

    public UpdateMessageHandler(@Autowired FindMessage findMessage, @Autowired Messages messages, @Autowired EventBus eventBus) {
        this.findMessage = findMessage;
        this.messages = messages;
        this.eventBus = eventBus;
    }

    public void handle(UpdateMessageCommand command) {
        Message message = this.findMessage.execute(new MessageId(command.getId()));

        message.edit(
                new MessageTitle(command.getMessageTitle()),
                new MessageBody(command.getMessageBody())
        );

        messages.save(message);
        eventBus.publish(message.pullDomainEvents());
    }
}
