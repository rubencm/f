package net.rubencm.forum.shared.infrastructure.command;

import net.rubencm.forum.shared.domain.command.Command;
import net.rubencm.forum.shared.domain.command.CommandHandler;
import net.rubencm.forum.shared.domain.command.CommandNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("CommandBusRegistry")
public class Registry {
    private final Map<Class<? extends Command>, Class<? extends CommandHandler>> commandHandlersMap = new HashMap<>();

    public Registry(@Autowired ApplicationContext applicationContext) {
        String[] names = applicationContext.getBeanNamesForType(CommandHandler.class);
        for (String name : names) {
            register(applicationContext, name);
        }
    }

    private void register(ApplicationContext applicationContext, String name) {
        Class<CommandHandler<?>> handlerClass = (Class<CommandHandler<?>>) applicationContext.getType(name);
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, CommandHandler.class);
        Class<? extends Command> commandType = (Class<? extends Command>) generics[0];

        commandHandlersMap.put(commandType, handlerClass);
    }

    public Class<? extends CommandHandler> get(Class<? extends Command> command) throws CommandNotRegisteredException {
        Class<? extends CommandHandler> handler = this.commandHandlersMap.get(command);

        if (handler == null) {
            throw new CommandNotRegisteredException();
        }

        return handler;
    }
}