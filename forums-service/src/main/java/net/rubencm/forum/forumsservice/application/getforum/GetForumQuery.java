package net.rubencm.forum.forumsservice.application.getforum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import net.rubencm.forum.shared.domain.query.Query;

@AllArgsConstructor
@Getter
public class GetForumQuery implements Query {
    @NonNull
    private String id;
}
