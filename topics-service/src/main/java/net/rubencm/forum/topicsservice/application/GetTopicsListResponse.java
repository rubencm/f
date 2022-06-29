package net.rubencm.forum.topicsservice.application;

import lombok.Data;
import net.rubencm.forum.shared.domain.query.Response;
import net.rubencm.forum.topicsservice.domain.aggregates.Topic;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetTopicsListResponse extends ArrayList<GetTopicResponse> implements Response {

    public static GetTopicsListResponse fromTopics(List<Topic> topics) {
        GetTopicsListResponse getAllForumsResponse = new GetTopicsListResponse();

        for (Topic topic : topics) {
            getAllForumsResponse.add(GetTopicResponse.fromTopic(topic));
        }

        return getAllForumsResponse;
    }
}