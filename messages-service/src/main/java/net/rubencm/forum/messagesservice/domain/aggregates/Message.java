package net.rubencm.forum.messagesservice.domain.aggregates;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.rubencm.forum.messagesservice.domain.valueobjects.MessageBody;
import net.rubencm.forum.messagesservice.domain.valueobjects.MessageTitle;
import net.rubencm.forum.messagesservice.domain.valueobjects.NumberModifications;
import net.rubencm.forum.messagesservice.domain.valueobjects.PosterName;
import net.rubencm.forum.shared.domain.Aggregate;
import net.rubencm.forum.shared.domain.events.message.MessageCreatedEvent;
import net.rubencm.forum.shared.domain.events.message.MessageUpdatedEvent;
import net.rubencm.forum.shared.domain.valueobjects.ForumId;
import net.rubencm.forum.shared.domain.valueobjects.MessageId;
import net.rubencm.forum.shared.domain.valueobjects.TopicId;

import java.util.Date;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class Message extends Aggregate {
    private MessageId id;
    private MessageTitle title;
    private MessageBody messageBody;
    private PosterName posterName;
    private NumberModifications numModifications;
    private Date creationDate;
    private Date lastModificationDate;
    private TopicId topicId;

    private Message(MessageId id, MessageTitle title, MessageBody messageBody, PosterName posterName, NumberModifications numModifications, Date creationDate, Date lastModificationDate, TopicId topicId) {
        this.id = id;
        this.title = title;
        this.messageBody = messageBody;
        this.posterName = posterName;
        this.numModifications = numModifications;
        this.creationDate = creationDate;
        this.lastModificationDate = lastModificationDate;
        this.topicId = topicId;
    }

    public static Message create(MessageId id, MessageTitle title, MessageBody messageBody, PosterName posterName, TopicId topicId, ForumId forumId) {
        Message message = new Message(id, title, messageBody, posterName, new NumberModifications(0), new Date(), new Date(), topicId);

        message.publish(new MessageCreatedEvent(id.value(), title.value(), posterName.value(), topicId.value(), forumId.value()));

        return message;
    }

    public void edit(MessageTitle title, MessageBody messageBody) {
        this.title = title;
        this.messageBody = messageBody;
        this.numModifications = new NumberModifications(this.numModifications.value() + 1);
        this.lastModificationDate = new Date();

        this.publish(new MessageUpdatedEvent(id.value(), title.value()));
    }
}
