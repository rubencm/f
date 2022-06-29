package net.rubencm.forum.shared.domain.command;

public interface CommandHandler<C extends Command> {
    void handle(C command);
}
