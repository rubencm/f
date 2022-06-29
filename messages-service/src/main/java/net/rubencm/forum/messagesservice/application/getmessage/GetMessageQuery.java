package net.rubencm.forum.messagesservice.application.getmessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import net.rubencm.forum.shared.domain.query.Query;

@AllArgsConstructor
@Getter
public class GetMessageQuery implements Query {
    @NonNull
    private String id;
}
