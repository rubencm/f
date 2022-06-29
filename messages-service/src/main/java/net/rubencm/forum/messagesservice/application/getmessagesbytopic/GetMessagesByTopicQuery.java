package net.rubencm.forum.messagesservice.application.getmessagesbytopic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import net.rubencm.forum.shared.domain.query.Query;

@Getter
@AllArgsConstructor
public class GetMessagesByTopicQuery implements Query {
    @NonNull
    private String topicId;
    @NonNull
    private Integer page;
}
