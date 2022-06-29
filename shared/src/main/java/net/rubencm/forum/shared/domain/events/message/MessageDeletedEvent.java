package net.rubencm.forum.shared.domain.events.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import net.rubencm.forum.shared.domain.event.DomainEvent;

@NoArgsConstructor
@Getter
public class MessageDeletedEvent extends DomainEvent {
    private String topicId;

    public MessageDeletedEvent(String aggregateId, String topicId) {
        super(aggregateId);
        this.topicId = topicId;
    }

    @Override
    public String eventName() {
        return "message.deleted";
    }
}
