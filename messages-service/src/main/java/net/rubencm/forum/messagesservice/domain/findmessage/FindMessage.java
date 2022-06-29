package net.rubencm.forum.messagesservice.domain.findmessage;

import net.rubencm.forum.messagesservice.domain.aggregates.Message;
import net.rubencm.forum.messagesservice.domain.repositories.Messages;
import net.rubencm.forum.shared.domain.valueobjects.MessageId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindMessage {
    final private Messages messages;

    public FindMessage(@Autowired Messages messages) {
        this.messages = messages;
    }

    public Message execute(MessageId id) {
        Optional<Message> forum = messages.byId(id);

        if (forum.isEmpty()) {
            throw new MessageNotFoundException();
        }

        return forum.get();
    }
}
