package net.rubencm.forum.shared.infrastructure.query;

import net.rubencm.forum.shared.domain.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class QueryBusImpl implements QueryBus {
    private final Registry registry;

    @Autowired
    private ApplicationContext context;

    public QueryBusImpl(@Autowired Registry registry) {
        this.registry = registry;
    }

    @Override
    public Response execute(Query query) {
        Class<? extends QueryHandler> queryHandler = null;
        try {
            queryHandler = registry.get(query.getClass());
        } catch (QueryNotRegisteredException e) {
            e.printStackTrace();
        }

        QueryHandler handler = context.getBean(queryHandler);

        return handler.handle(query);
    }
}
