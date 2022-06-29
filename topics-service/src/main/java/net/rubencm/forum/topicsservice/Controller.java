package net.rubencm.forum.topicsservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import net.rubencm.forum.shared.domain.command.CommandBus;
import net.rubencm.forum.shared.domain.query.QueryBus;
import net.rubencm.forum.topicsservice.application.GetTopicResponse;
import net.rubencm.forum.topicsservice.application.GetTopicsListResponse;
import net.rubencm.forum.topicsservice.application.deletetopic.DeleteTopicCommand;
import net.rubencm.forum.topicsservice.application.getalltopics.GetAllTopicsQuery;
import net.rubencm.forum.topicsservice.application.gettopic.GetTopicQuery;
import net.rubencm.forum.topicsservice.application.gettopicsbyforum.GetTopicsByForumQuery;
import net.rubencm.forum.topicsservice.domain.findtopic.TopicNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public ResponseEntity<Object> getTopic(@PathVariable("id") String id) {

        GetTopicResponse topicResponse = queryBus.execute(
                new GetTopicQuery(id)
        );

        return new ResponseEntity<>(Response.fromGetTopicResponse(topicResponse), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Object> getTopics() {

        GetTopicsListResponse allForumsResponse = queryBus.execute(
                new GetAllTopicsQuery()
        );

        return new ResponseEntity<>(allForumsResponse.stream().map(Response::fromGetTopicResponse).collect(Collectors.toList()), HttpStatus.OK);

    }

    @RequestMapping(value = "/byforum/{forumId}", method = RequestMethod.GET)
    public ResponseEntity<Object> searchTopics(@PathVariable("forumId") String forumId, @RequestParam(value = "page") Integer page) {

        GetTopicsListResponse topicListResponse = queryBus.execute(
                new GetTopicsByForumQuery(forumId, page)
        );

        return new ResponseEntity<>(topicListResponse.stream().map(Response::fromGetTopicResponse).collect(Collectors.toList()), HttpStatus.OK);
    }

    @ExceptionHandler({TopicNotFoundException.class})
    public ResponseEntity<Object> handleException() {
        return new ResponseEntity<>("Topic not found", HttpStatus.NOT_FOUND);
    }
}

@Data
@AllArgsConstructor
class Response {
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

    public static Response fromGetTopicResponse(GetTopicResponse getForumResponse) {
        return new Response(
                getForumResponse.getId(),
                getForumResponse.getTitle(),
                getForumResponse.getLastPosterName(),
                getForumResponse.getLastPostDate(),
                getForumResponse.getNumMessages(),
                getForumResponse.getCreationDate(),
                getForumResponse.getAuthor(),
                getForumResponse.getForumId()
        );
    }
}

