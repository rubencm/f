package net.rubencm.forum.messagesservice.infrastructure.hibernate;

import net.rubencm.forum.messagesservice.domain.aggregates.Message;
import net.rubencm.forum.messagesservice.domain.repositories.Messages;
import net.rubencm.forum.shared.domain.valueobjects.MessageId;
import net.rubencm.forum.shared.domain.valueobjects.TopicId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements Messages {
    protected final MessageRepository messageRepository;

    @PersistenceContext
    EntityManager em;

    public MessageService(@Autowired MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void save(Message message) {
        this.messageRepository.save(message);
        this.messageRepository.flush();
    }

    @Override
    public void delete(Message message) {
        this.messageRepository.delete(message);
        this.messageRepository.flush();
    }

    @Override
    public Optional<Message> byId(MessageId id) {
        return this.messageRepository.findById(id);
    }

    @Override
    public List<Message> all() {
        return this.messageRepository.findAll();
    }

    @Override
    public List<Message> byTopic(TopicId topicId) {
        return this.messageRepository.findAllByTopicId(topicId);
    }

    @Override
    public List<Message> byTopic(TopicId topicId, Integer page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("creationDate").ascending());
        return this.messageRepository.findAllByTopicId(topicId, pageable);
    }
}
