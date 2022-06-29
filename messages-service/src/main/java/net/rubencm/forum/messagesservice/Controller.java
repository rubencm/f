package net.rubencm.forum.messagesservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import net.rubencm.forum.messagesservice.application.GetMessageResponse;
import net.rubencm.forum.messagesservice.application.GetMessagesListResponse;
import net.rubencm.forum.messagesservice.application.createmessage.CreateMessageCommand;
import net.rubencm.forum.messagesservice.application.deletemessage.DeleteMessageCommand;
import net.rubencm.forum.messagesservice.application.getallmessages.GetAllMessagesQuery;
import net.rubencm.forum.messagesservice.application.getmessage.GetMessageQuery;
import net.rubencm.forum.messagesservice.application.getmessagesbytopic.GetMessagesByTopicQuery;
import net.rubencm.forum.messagesservice.application.updatemessage.UpdateMessageCommand;
import net.rubencm.forum.messagesservice.domain.findmessage.MessageNotFoundException;
import net.rubencm.forum.shared.domain.command.CommandBus;
import net.rubencm.forum.shared.domain.query.QueryBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class Controller {

    final private CommandBus commandBus;
    final private QueryBus queryBus;

    @Autowired
    public Controller(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getMessage(@PathVariable("id") String id) {

        GetMessageResponse messageResponse = queryBus.execute(
                new GetMessageQuery(id)
        );

        return new ResponseEntity<>(Response.fromGetMessageResponse(messageResponse), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Object> createMessage(@Valid @RequestBody CreateRequest createRequest) {

        UUID id = UUID.randomUUID();

        String topicId = createRequest.getTopicId();
        if (topicId == null) {
            topicId = id.toString();
        }

        this.commandBus.execute(
                new CreateMessageCommand(
                        id.toString(),
                        createRequest.getMessageTitle(),
                        createRequest.getMessageBody(),
                        createRequest.getPosterName(),
                        topicId,
                        createRequest.getForumId()
                )
        );

        GetMessageResponse messageResponse = queryBus.execute(
                new GetMessageQuery(id.toString())
        );

        return new ResponseEntity<>(Response.fromGetMessageResponse(messageResponse), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateMessage(@Valid @RequestBody UpdateRequest updateRequest, @PathVariable("id") String id) {

        this.commandBus.execute(
                new UpdateMessageCommand(
                        id,
                        updateRequest.getMessageTitle(),
                        updateRequest.getMessageBody()
                )
        );

        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteMessage(@PathVariable("id") String id) {

        this.commandBus.execute(
                new DeleteMessageCommand(id)
        );

        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/bytopic/{topicId}", method = RequestMethod.GET)
    public ResponseEntity<Object> searchTopics(@PathVariable("topicId") String topicId, @RequestParam(value = "page") Integer page) {

        GetMessagesListResponse messagesListResponse = queryBus.execute(
                new GetMessagesByTopicQuery(topicId, page)
        );

        return new ResponseEntity<>(messagesListResponse.stream().map(Response::fromGetMessageResponse).collect(Collectors.toList()), HttpStatus.OK);
    }

    @ExceptionHandler({MessageNotFoundException.class})
    public ResponseEntity<Object> handleException() {
        return new ResponseEntity<>("Message not found", HttpStatus.NOT_FOUND);
    }
}

@Data
@AllArgsConstructor
class UpdateRequest {
    @NotNull(message = "Title cannot be null")
    @Size(min = 5, max = 100, message = "Title must have between 5 and 100 characters")
    private String messageTitle;

    @NotNull(message = "Message cannot be null")
    @Size(min = 3, max = 4000, message = "Message must be between 3 and 4000 characters")
    private String messageBody;
}

@Data
@AllArgsConstructor
class CreateRequest {
    @NotNull(message = "Title cannot be null")
    @Size(min = 5, max = 100, message = "Title must have between 5 and 100 characters")
    private String messageTitle;

    @NotNull(message = "Message cannot be null")
    @Size(min = 3, max = 4000, message = "Message must be between 3 and 4000 characters")
    private String messageBody;

    @NotNull(message = "Username cannot be null")
    @Size(min = 5, max = 20, message = "Username must have between 5 and 20 characters")
    private String posterName;

    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$")
    private String topicId;

    @NotNull(message = "forumId cannot be null")
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$")
    private String forumId;
}

@Data
@AllArgsConstructor
class Response {
    @NonNull
    private String id;
    @NonNull
    private String title;
    @NonNull
    private String message;
    @NonNull
    private String posterName;
    @NonNull
    private Integer numModifications;
    @NonNull
    private Date creationDate;
    @NonNull
    private Date lastModificationDate;
    @NonNull
    private String topicId;

    public static Response fromGetMessageResponse(GetMessageResponse getMessageResponse) {
        return new Response(
                getMessageResponse.getId(),
                getMessageResponse.getTitle(),
                getMessageResponse.getMessage(),
                getMessageResponse.getPosterName(),
                getMessageResponse.getNumModifications(),
                getMessageResponse.getCreationDate(),
                getMessageResponse.getLastModificationDate(),
                getMessageResponse.getTopicId()
        );
    }
}

