package net.rubencm.forum.shared.domain.events.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import net.rubencm.forum.shared.domain.event.DomainEvent;

@NoArgsConstructor
@Getter
public class MessageCreatedEvent extends DomainEvent {
    private String title;

    private String posterName;

    private String topicId;

    private String forumId;

    public MessageCreatedEvent(@NonNull String aggregateId, @NonNull String title, @NonNull String posterName, @NonNull String topicId, @NonNull String forumId) {
        super(aggregateId);
        this.title = title;
        this.posterName = posterName;
        this.topicId = topicId;
        this.forumId = forumId;
    }

    @Override
    public String eventName() {
        return "message.created";
    }
}
