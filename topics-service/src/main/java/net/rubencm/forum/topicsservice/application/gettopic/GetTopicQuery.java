package net.rubencm.forum.topicsservice.application.gettopic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import net.rubencm.forum.shared.domain.query.Query;

@AllArgsConstructor
@Getter
public class GetTopicQuery implements Query {
    @NonNull
    private String id;
}
