package net.rubencm.forum.forumsservice.application.getforum;

import net.rubencm.forum.forumsservice.application.GetForumResponse;
import net.rubencm.forum.forumsservice.domain.aggregates.Forum;
import net.rubencm.forum.forumsservice.domain.services.findforum.FindForum;
import net.rubencm.forum.shared.domain.query.QueryHandler;
import net.rubencm.forum.shared.domain.valueobjects.ForumId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetForumHandler implements QueryHandler<GetForumQuery, GetForumResponse> {
    final private FindForum findForum;

    public GetForumHandler(@Autowired FindForum findForum) {
        this.findForum = findForum;
    }

    @Override
    public GetForumResponse handle(GetForumQuery query) {
        Forum forum = this.findForum.execute(new ForumId(query.getId()));

        return GetForumResponse.fromForum(forum);
    }
}
