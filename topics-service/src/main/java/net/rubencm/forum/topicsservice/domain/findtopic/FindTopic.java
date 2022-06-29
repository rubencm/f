package net.rubencm.forum.topicsservice.domain.findtopic;

import net.rubencm.forum.shared.domain.valueobjects.TopicId;
import net.rubencm.forum.topicsservice.domain.aggregates.Topic;
import net.rubencm.forum.topicsservice.domain.repositories.Topics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindTopic {
    final private Topics topics;

    public FindTopic(@Autowired Topics topics) {
        this.topics = topics;
    }

    public Topic execute(TopicId id) {
        Optional<Topic> forum = topics.byId(id);

        if (forum.isEmpty()) {
            throw new TopicNotFoundException();
        }

        return forum.get();
    }
}
