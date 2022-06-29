package net.rubencm.forum.messagesservice.application.getmessagesbytopic;

import net.rubencm.forum.messagesservice.application.GetMessagesListResponse;
import net.rubencm.forum.messagesservice.domain.aggregates.Message;
import net.rubencm.forum.messagesservice.domain.repositories.Messages;
import net.rubencm.forum.shared.domain.query.QueryHandler;
import net.rubencm.forum.shared.domain.valueobjects.TopicId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetMessagesByTopicHandler implements QueryHandler<GetMessagesByTopicQuery, GetMessagesListResponse> {
    final private Messages messages;

    public GetMessagesByTopicHandler(Messages messages) {
        this.messages = messages;
    }

    @Override
    public GetMessagesListResponse handle(GetMessagesByTopicQuery query) {

        List<Message> messages = this.messages.byTopic(new TopicId(query.getTopicId()), query.getPage());

        return GetMessagesListResponse.fromMessages(messages);
    }
}
