package net.rubencm.forum.forumsservice.infrastructure.persistence.hibernate;

import net.rubencm.forum.forumsservice.domain.aggregates.Forum;
import net.rubencm.forum.shared.domain.valueobjects.ForumId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumRepository extends JpaRepository<Forum, ForumId> {
}
