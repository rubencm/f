package net.rubencm.forum.shared.domain.event;

public interface DomainEventListener<T extends DomainEvent> {
    void on(T event);
}
