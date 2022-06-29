package net.rubencm.forum.forumsservice.application.getallforums;

import net.rubencm.forum.forumsservice.application.GetForumsListResponse;
import net.rubencm.forum.forumsservice.domain.aggregates.Forum;
import net.rubencm.forum.forumsservice.domain.repositories.Forums;
import net.rubencm.forum.shared.domain.query.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllForumsHandler implements QueryHandler<GetAllForumsQuery, GetForumsListResponse> {
    final private Forums forums;

    public GetAllForumsHandler(@Autowired Forums forums) {
        this.forums = forums;
    }

    @Override
    public GetForumsListResponse handle(GetAllForumsQuery query) {
        List<Forum> forums = this.forums.all();

        return GetForumsListResponse.fromForums(forums);
    }
}
