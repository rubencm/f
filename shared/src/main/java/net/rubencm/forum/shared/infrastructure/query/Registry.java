package net.rubencm.forum.shared.infrastructure.query;

import net.rubencm.forum.shared.domain.query.Query;
import net.rubencm.forum.shared.domain.query.QueryHandler;
import net.rubencm.forum.shared.domain.query.QueryNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("QueryBusRegistry")
public class Registry {
    private final Map<Class<? extends Query>, Class<? extends QueryHandler>> queryHandlersMap = new HashMap<>();

    public Registry(@Autowired ApplicationContext applicationContext) {
        String[] names = applicationContext.getBeanNamesForType(QueryHandler.class);
        for (String name : names) {
            register(applicationContext, name);
        }
    }

    private void register(ApplicationContext applicationContext, String name) {
        Class<QueryHandler<?, ?>> handlerClass = (Class<QueryHandler<?, ?>>) applicationContext.getType(name);
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, QueryHandler.class);
        Class<? extends Query> queryType = (Class<? extends Query>) generics[0];

        queryHandlersMap.put(queryType, handlerClass);
    }

    public Class<? extends QueryHandler> get(Class<? extends Query> query) throws QueryNotRegisteredException {
        Class<? extends QueryHandler> handler = this.queryHandlersMap.get(query);

        if (handler == null) {
            throw new QueryNotRegisteredException();
        }

        return handler;
    }
}