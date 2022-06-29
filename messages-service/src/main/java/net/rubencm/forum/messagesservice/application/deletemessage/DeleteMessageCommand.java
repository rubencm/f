package net.rubencm.forum.messagesservice.application.deletemessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import net.rubencm.forum.shared.domain.command.Command;

@Getter
@AllArgsConstructor
public class DeleteMessageCommand implements Command {
    @NonNull
    private String id;
}
