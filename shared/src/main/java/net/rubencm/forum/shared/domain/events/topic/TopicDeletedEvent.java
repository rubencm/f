package net.rubencm.forum.shared.domain.events.topic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import net.rubencm.forum.shared.domain.event.DomainEvent;

@NoArgsConstructor
@Getter
public class TopicDeletedEvent extends DomainEvent {
    private String forumId;

    public TopicDeletedEvent(@NonNull String aggregateId, @NonNull String forumId) {
        super(aggregateId);
        this.forumId = forumId;
    }

    @Override
    public String eventName() {
        return "topic.deleted";
    }
}
