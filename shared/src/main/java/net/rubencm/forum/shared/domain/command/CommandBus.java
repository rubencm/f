package net.rubencm.forum.shared.domain.command;

public interface CommandBus {
    void execute(Command command);
}
