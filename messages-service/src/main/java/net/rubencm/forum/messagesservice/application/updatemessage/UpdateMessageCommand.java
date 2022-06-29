package net.rubencm.forum.messagesservice.application.updatemessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import net.rubencm.forum.shared.domain.command.Command;

@Getter
@AllArgsConstructor
public class UpdateMessageCommand implements Command {
    @NonNull
    private String id;
    @NonNull
    private String messageTitle;
    @NonNull
    private String messageBody;
}