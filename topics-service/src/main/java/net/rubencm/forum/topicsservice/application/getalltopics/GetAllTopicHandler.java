package net.rubencm.forum.topicsservice.application.getalltopics;

import net.rubencm.forum.shared.domain.query.QueryHandler;
import net.rubencm.forum.topicsservice.application.GetTopicsListResponse;
import net.rubencm.forum.topicsservice.domain.aggregates.Topic;
import net.rubencm.forum.topicsservice.domain.repositories.Topics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllTopicHandler implements QueryHandler<GetAllTopicsQuery, GetTopicsListResponse> {
    final private Topics topics;

    public GetAllTopicHandler(@Autowired Topics topics) {
        this.topics = topics;
    }

    @Override
    public GetTopicsListResponse handle(GetAllTopicsQuery query) {
        List<Topic> topics = this.topics.all();

        return GetTopicsListResponse.fromTopics(topics);
    }
}
