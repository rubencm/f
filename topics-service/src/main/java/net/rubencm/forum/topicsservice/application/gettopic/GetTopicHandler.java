package net.rubencm.forum.topicsservice.application.gettopic;

import net.rubencm.forum.shared.domain.query.QueryHandler;
import net.rubencm.forum.shared.domain.valueobjects.TopicId;
import net.rubencm.forum.topicsservice.application.GetTopicResponse;
import net.rubencm.forum.topicsservice.domain.aggregates.Topic;
import net.rubencm.forum.topicsservice.domain.findtopic.FindTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetTopicHandler implements QueryHandler<GetTopicQuery, GetTopicResponse> {
    final private FindTopic findTopic;

    @Autowired
    public GetTopicHandler(FindTopic findTopic) {
        this.findTopic = findTopic;
    }

    @Override
    public GetTopicResponse handle(GetTopicQuery query) {
        Topic topic = this.findTopic.execute(new TopicId(query.getId()));

        return GetTopicResponse.fromTopic(topic);
    }
}
