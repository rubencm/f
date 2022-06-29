package net.rubencm.forum.topicsservice.application.gettopicsbyforum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import net.rubencm.forum.shared.domain.query.Query;

@Getter
@AllArgsConstructor
public class GetTopicsByForumQuery implements Query {
    @NonNull
    private String forumId;
    @NonNull
    private Integer page;
}
