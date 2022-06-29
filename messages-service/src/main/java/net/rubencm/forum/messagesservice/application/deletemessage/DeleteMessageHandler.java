package net.rubencm.forum.messagesservice.application.deletemessage;

import net.rubencm.forum.messagesservice.domain.aggregates.Message;
import net.rubencm.forum.messagesservice.domain.findmessage.FindMessage;
import net.rubencm.forum.messagesservice.domain.repositories.Messages;
import net.rubencm.forum.shared.domain.command.CommandHandler;
import net.rubencm.forum.shared.domain.event.EventBus;
import net.rubencm.forum.shared.domain.events.message.MessageDeletedEvent;
import net.rubencm.forum.shared.domain.valueobjects.MessageId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteMessageHandler implements CommandHandler<DeleteMessageCommand> {
    final private FindMessage findMessage;
    final private Messages messages;

    final private EventBus eventBus;

    @Autowired
    public DeleteMessageHandler(FindMessage findMessage, Messages messages, @Autowired EventBus eventBus) {
        this.findMessage = findMessage;
        this.messages = messages;
        this.eventBus = eventBus;
    }

    public void handle(DeleteMessageCommand command) {
        Message message = this.findMessage.execute(new MessageId(command.getId()));

        messages.delete(message);

        if (message.getId().value().equals(message.getTopicId().value())) { // If message deleted is a topic

            List<Message> childrenMessages = messages.byTopic(message.getTopicId());

            for (Message childrenMessage : childrenMessages) {
                messages.delete(childrenMessage);
            }

        }

        eventBus.publish(new MessageDeletedEvent(message.getId().value(), message.getTopicId().value()));
    }
}
