package net.rubencm.forum.shared.domain;

import net.rubencm.forum.shared.domain.event.DomainEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Aggregate {
    private List<DomainEvent> domainEvents = new ArrayList<>();

    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = domainEvents;

        domainEvents = Collections.emptyList();

        return events;
    }

    protected void publish(DomainEvent event) {
        domainEvents.add(event);
    }
}
