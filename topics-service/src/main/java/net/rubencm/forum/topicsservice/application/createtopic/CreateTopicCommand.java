package net.rubencm.forum.topicsservice.application.createtopic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import net.rubencm.forum.shared.domain.command.Command;

@Getter
@AllArgsConstructor
public class CreateTopicCommand implements Command {
    @NonNull
    private String id;
    @NonNull
    private String title;
    @NonNull
    private String author;
    @NonNull
    private String forumId;
}
