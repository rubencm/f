package net.rubencm.forum.topicsservice.infrastructure.hibernate;

import net.rubencm.forum.shared.domain.valueobjects.ForumId;
import net.rubencm.forum.shared.domain.valueobjects.TopicId;
import net.rubencm.forum.topicsservice.domain.aggregates.Topic;
import net.rubencm.forum.topicsservice.domain.repositories.Topics;
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
public class TopicService implements Topics {
    protected final TopicRepository topicRepository;

    @PersistenceContext
    EntityManager em;

    public TopicService(@Autowired TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public void save(Topic forum) {
        this.topicRepository.save(forum);
        this.topicRepository.flush();
    }

    @Override
    public void delete(Topic forum) {
        this.topicRepository.delete(forum);
        this.topicRepository.flush();
    }

    @Override
    public Optional<Topic> byId(TopicId id) {
        return this.topicRepository.findById(id);
    }

    @Override
    public List<Topic> all() {
        return this.topicRepository.findAll();
    }

    @Override
    public List<Topic> byForum(ForumId forumId, Integer page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("lastPostDate").descending());
        return this.topicRepository.findAllByForumId(forumId, pageable);
    }
}
