package net.rubencm.forum.messagesservice.domain.repositories;

import net.rubencm.forum.messagesservice.domain.aggregates.Message;
import net.rubencm.forum.shared.domain.valueobjects.MessageId;
import net.rubencm.forum.shared.domain.valueobjects.TopicId;

import java.util.List;
import java.util.Optional;

public interface Messages {
    void save(Message message);

    void delete(Message message);

    Optional<Message> byId(MessageId id);

    List<Message> all();

    List<Message> byTopic(TopicId topicId);

    List<Message> byTopic(TopicId topicId, Integer page);
}
