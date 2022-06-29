package net.rubencm.forum.topicsservice.application.deletetopic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import net.rubencm.forum.shared.domain.command.Command;

@Getter
@AllArgsConstructor
public class DeleteTopicCommand implements Command {
    @NonNull
    private String id;
}
