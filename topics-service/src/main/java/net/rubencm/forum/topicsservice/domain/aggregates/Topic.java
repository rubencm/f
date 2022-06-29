package net.rubencm.forum.topicsservice.domain.aggregates;

import lombok.*;
import net.rubencm.forum.shared.domain.Aggregate;
import net.rubencm.forum.shared.domain.events.topic.TopicCreatedEvent;
import net.rubencm.forum.shared.domain.valueobjects.ForumId;
import net.rubencm.forum.shared.domain.valueobjects.TopicId;
import net.rubencm.forum.shared.domain.valueobjects.User;
import net.rubencm.forum.topicsservice.domain.valueobjects.NumberMessages;
import net.rubencm.forum.topicsservice.domain.valueobjects.TopicTitle;

import java.util.Date;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class Topic extends Aggregate {
    private TopicId id;
    private TopicTitle title;
    private User lastPosterName;
    private Date lastPostDate;
    private NumberMessages numMessages;
    private Date creationDate;
    private User author;
    private ForumId forumId;

    protected Topic(TopicId id, TopicTitle title, User lastPosterName, Date lastPostDate, NumberMessages numMessages, Date creationDate, User author, ForumId forumId) {
        this.id = id;
        this.title = title;
        this.lastPosterName = lastPosterName;
        this.lastPostDate = lastPostDate;
        this.numMessages = numMessages;
        this.creationDate = creationDate;
        this.author = author;
        this.forumId = forumId;
    }

    public static Topic create(@NonNull TopicId id, @NonNull TopicTitle title, @NonNull User author, @NonNull ForumId forumId) {
        Topic topic = new Topic(id, title, new User(""), new Date(), new NumberMessages(0), new Date(), author, forumId);

        topic.publish(new TopicCreatedEvent(id.value(), forumId.value()));

        return topic;
    }

    public void edit(@NonNull TopicTitle title) {
        this.title = title;
    }

    public void addMessage(@NonNull User lastPosterName) {
        this.lastPosterName = lastPosterName;
        this.lastPostDate = new Date();
        this.numMessages = new NumberMessages(this.getNumMessages().value() + 1);
    }

    public void removeMessage() {
        this.numMessages = new NumberMessages(this.getNumMessages().value() - 1);
    }
}
