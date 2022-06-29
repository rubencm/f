package net.rubencm.forum.shared.domain.query;

public interface QueryBus {
    <R> R execute(Query query);
}
