package net.rubencm.forum.messagesservice.infrastructure.hibernate;

import net.rubencm.forum.messagesservice.domain.aggregates.Message;
import net.rubencm.forum.shared.domain.valueobjects.MessageId;
import net.rubencm.forum.shared.domain.valueobjects.TopicId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, MessageId> {
    List<Message> findAllByTopicId(TopicId topicId);

    List<Message> findAllByTopicId(TopicId topicId, Pageable pageable);
}
