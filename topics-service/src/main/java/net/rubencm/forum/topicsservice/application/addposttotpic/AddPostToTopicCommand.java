package net.rubencm.forum.topicsservice.application.addposttotpic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import net.rubencm.forum.shared.domain.command.Command;

@Getter
@AllArgsConstructor
public class AddPostToTopicCommand implements Command {
    @NonNull
    private String id;
    @NonNull
    private String posterName;
}
