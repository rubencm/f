package net.rubencm.forum.topicsservice.domain.repositories;

import net.rubencm.forum.shared.domain.valueobjects.ForumId;
import net.rubencm.forum.shared.domain.valueobjects.TopicId;
import net.rubencm.forum.topicsservice.domain.aggregates.Topic;

import java.util.List;
import java.util.Optional;

public interface Topics {
    void save(Topic forum);

    void delete(Topic forum);

    Optional<Topic> byId(TopicId id);

    List<Topic> all();

    List<Topic> byForum(ForumId forumId, Integer page);
}
