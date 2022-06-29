package net.rubencm.forum.shared.domain.query;

public interface QueryHandler<Q extends Query, R extends Response> {
    R handle(Q query);
}
