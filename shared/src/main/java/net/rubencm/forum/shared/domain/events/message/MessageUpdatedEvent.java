package net.rubencm.forum.shared.domain.events.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import net.rubencm.forum.shared.domain.event.DomainEvent;

@NoArgsConstructor
@Getter
public class MessageUpdatedEvent extends DomainEvent {
    private String title;

    public MessageUpdatedEvent(@NonNull String aggregateId, @NonNull String title) {
        super(aggregateId);
        this.title = title;
    }

    @Override
    public String eventName() {
        return "message.updated";
    }
}
