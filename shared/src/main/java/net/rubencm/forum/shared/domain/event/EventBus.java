package net.rubencm.forum.shared.domain.event;

import java.util.List;

public interface EventBus {
    void publish(DomainEvent event);

    void publish(List<DomainEvent> events);
}
