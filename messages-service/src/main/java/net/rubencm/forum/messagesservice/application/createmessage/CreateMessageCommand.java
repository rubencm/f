package net.rubencm.forum.messagesservice.application.createmessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import net.rubencm.forum.shared.domain.command.Command;

@Getter
@AllArgsConstructor
public class CreateMessageCommand implements Command {
    @NonNull
    private String id;
    @NonNull
    private String messageTitle;
    @NonNull
    private String messageBody;
    @NonNull
    private String posterName;
    @NonNull
    private String topicId;
    @NonNull
    private String forumId;
}
