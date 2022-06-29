package net.rubencm.forum.shared.infrastructure.command;

import net.rubencm.forum.shared.domain.command.Command;
import net.rubencm.forum.shared.domain.command.CommandBus;
import net.rubencm.forum.shared.domain.command.CommandHandler;
import net.rubencm.forum.shared.domain.command.CommandNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CommandBusImpl implements CommandBus {
    private final Registry registry;

    @Autowired
    private ApplicationContext context;

    public CommandBusImpl(@Autowired Registry registry) {
        this.registry = registry;
    }

    @Override
    public void execute(Command command) {
        Class<? extends CommandHandler> commandHandler = null;

        try {
            commandHandler = registry.get(command.getClass());
        } catch (CommandNotRegisteredException e) {
            e.printStackTrace();
        }

        CommandHandler handler = context.getBean(commandHandler);

        handler.handle(command);
    }
}
