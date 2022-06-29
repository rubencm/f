package net.rubencm.forum.shared.infrastructure.event.rabbitmq;

import net.rubencm.forum.shared.domain.event.DomainEvent;
import net.rubencm.forum.shared.domain.event.DomainEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;

@Service
public class DomainEventRegistry {
    private final Map<String, List<Class<? extends DomainEventListener>>> eventsMap = new HashMap<>();

    public DomainEventRegistry(@Autowired ApplicationContext applicationContext) {
        String[] names = applicationContext.getBeanNamesForType(DomainEventListener.class);
        for (String name : names) {
            register(applicationContext, name);
        }
    }

    private void register(ApplicationContext applicationContext, String name) {
        Class<DomainEventListener<?>> listenerClass = (Class<DomainEventListener<?>>) applicationContext.getType(name);
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(listenerClass, DomainEventListener.class);
        Class<? extends DomainEvent> eventClass = (Class<? extends DomainEvent>) generics[0];

        try {
            Method method = eventClass.getDeclaredMethod("eventName");
            DomainEvent domainEventInstance = eventClass.getDeclaredConstructor().newInstance();
            String eventName = (String) method.invoke(domainEventInstance);

            if (!this.eventsMap.containsValue(eventName)) {
                this.eventsMap.put(eventName, new ArrayList<>());
            }

            this.eventsMap.get(eventName).add(listenerClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<String> getEvents() {
        return this.eventsMap.keySet();
    }

    public List<Class<? extends DomainEventListener>> getListeners(String eventName) {
        List<Class<? extends DomainEventListener>> listener = this.eventsMap.get(eventName);

        if (listener == null) {
            throw new RuntimeException("Listener not found");
        }

        return listener;
    }
}
