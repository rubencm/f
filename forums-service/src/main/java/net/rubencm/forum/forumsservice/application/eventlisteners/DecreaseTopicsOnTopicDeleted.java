package net.rubencm.forum.forumsservice.application.eventlisteners;

import net.rubencm.forum.forumsservice.domain.aggregates.Forum;
import net.rubencm.forum.forumsservice.domain.repositories.Forums;
import net.rubencm.forum.forumsservice.domain.services.findforum.FindForum;
import net.rubencm.forum.shared.domain.event.DomainEventListener;
import net.rubencm.forum.shared.domain.event.EventBus;
import net.rubencm.forum.shared.domain.events.topic.TopicDeletedEvent;
import net.rubencm.forum.shared.domain.valueobjects.ForumId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DecreaseTopicsOnTopicDeleted implements DomainEventListener<TopicDeletedEvent> {
    final private FindForum findForum;
    final private Forums forums;

    final private EventBus eventBus;

    @Autowired
    public DecreaseTopicsOnTopicDeleted(FindForum findForum, Forums forums, EventBus eventBus) {
        this.findForum = findForum;
        this.forums = forums;
        this.eventBus = eventBus;
    }

    @Override
    public void on(TopicDeletedEvent event) {
        Forum forum = this.findForum.execute(new ForumId(event.getForumId()));

        forum.decreaseTopics();

        forums.save(forum);
        eventBus.publish(forum.pullDomainEvents());
    }
}
