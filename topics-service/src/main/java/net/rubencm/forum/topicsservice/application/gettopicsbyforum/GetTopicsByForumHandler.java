package net.rubencm.forum.topicsservice.application.gettopicsbyforum;

import net.rubencm.forum.shared.domain.query.QueryHandler;
import net.rubencm.forum.shared.domain.valueobjects.ForumId;
import net.rubencm.forum.topicsservice.application.GetTopicsListResponse;
import net.rubencm.forum.topicsservice.domain.aggregates.Topic;
import net.rubencm.forum.topicsservice.domain.repositories.Topics;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTopicsByForumHandler implements QueryHandler<GetTopicsByForumQuery, GetTopicsListResponse> {
    final private Topics topics;

    public GetTopicsByForumHandler(Topics topics) {
        this.topics = topics;
    }

    @Override
    public GetTopicsListResponse handle(GetTopicsByForumQuery query) {

        List<Topic> topics = this.topics.byForum(new ForumId(query.getForumId()), query.getPage());

        return GetTopicsListResponse.fromTopics(topics);
    }
}
