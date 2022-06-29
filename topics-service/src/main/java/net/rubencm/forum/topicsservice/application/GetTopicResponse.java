package net.rubencm.forum.topicsservice.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import net.rubencm.forum.shared.domain.query.Response;
import net.rubencm.forum.topicsservice.domain.aggregates.Topic;

import java.util.Date;

@Data
@AllArgsConstructor
public class GetTopicResponse implements Response {
    @NonNull
    private String id;

    @NonNull
    private String title;

    @NonNull
    private String lastPosterName;

    @NonNull
    private Date lastPostDate;

    @NonNull
    private Integer numMessages;

    @NonNull
    private Date creationDate;

    @NonNull
    private String author;

    @NonNull
    private String forumId;

    public static GetTopicResponse fromTopic(Topic topic) {
        return new GetTopicResponse(
                topic.getId().value(),
                topic.getTitle().value(),
                topic.getLastPosterName().value(),
                topic.getLastPostDate(),
                topic.getNumMessages().value(),
                topic.getCreationDate(),
                topic.getAuthor().value(),
                topic.getForumId().value()
        );
    }
}
