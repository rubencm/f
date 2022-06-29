package net.rubencm.forum.forumsservice.infrastructure.persistence.hibernate;

import net.rubencm.forum.forumsservice.domain.aggregates.Forum;
import net.rubencm.forum.forumsservice.domain.repositories.Forums;
import net.rubencm.forum.shared.domain.valueobjects.ForumId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class ForumService implements Forums {
    protected final ForumRepository forumRepository;

    @PersistenceContext
    EntityManager em;

    public ForumService(@Autowired ForumRepository forumRepository) {
        this.forumRepository = forumRepository;
    }

    @Override
    public void save(Forum forum) {
        this.forumRepository.save(forum);
        this.forumRepository.flush();
    }

    @Override
    public void delete(Forum forum) {
        this.forumRepository.delete(forum);
        this.forumRepository.flush();
    }

    @Override
    public Optional<Forum> byId(ForumId id) {
        return this.forumRepository.findById(id);
    }

    @Override
    public List<Forum> all() {
        return this.forumRepository.findAll();
    }
}
