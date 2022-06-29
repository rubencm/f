package net.rubencm.forum.forumsservice.application;

import lombok.Data;
import net.rubencm.forum.forumsservice.domain.aggregates.Forum;
import net.rubencm.forum.shared.domain.query.Response;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetForumsListResponse extends ArrayList<GetForumResponse> implements Response {

    public static GetForumsListResponse fromForums(List<Forum> forums) {
        GetForumsListResponse getAllForumsResponse = new GetForumsListResponse();

        for (Forum forum : forums) {
            getAllForumsResponse.add(GetForumResponse.fromForum(forum));
        }

        return getAllForumsResponse;
    }
}