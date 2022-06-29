package net.rubencm.forum.forumsservice.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import net.rubencm.forum.forumsservice.domain.aggregates.Forum;
import net.rubencm.forum.shared.domain.query.Response;

@Data
@AllArgsConstructor
public class GetForumResponse implements Response {
    @NonNull
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private String icon;
    @NonNull
    private Integer numTopics;


    public static GetForumResponse fromForum(Forum forum) {
        return new GetForumResponse(
                forum.getId().value(),
                forum.getName().value(),
                forum.getDescription().value(),
                forum.getIcon().value(),
                forum.getNumTopics().value()
        );
    }
}
