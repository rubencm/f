package net.rubencm.forum.forumsservice.domain.repositories;

import net.rubencm.forum.forumsservice.domain.aggregates.Forum;
import net.rubencm.forum.shared.domain.valueobjects.ForumId;

import java.util.List;
import java.util.Optional;

public interface Forums {
    void save(Forum forum);

    void delete(Forum forum);

    Optional<Forum> byId(ForumId id);

    List<Forum> all();
}
