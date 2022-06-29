package net.rubencm.forum.forumsservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import net.rubencm.forum.forumsservice.application.GetForumResponse;
import net.rubencm.forum.forumsservice.application.GetForumsListResponse;
import net.rubencm.forum.forumsservice.application.getallforums.GetAllForumsQuery;
import net.rubencm.forum.forumsservice.application.getforum.GetForumQuery;
import net.rubencm.forum.forumsservice.domain.services.findforum.ForumNotFoundException;
import net.rubencm.forum.shared.domain.query.QueryBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@RestController
public class Controller {

    final private QueryBus queryBus;

    @Autowired
    public Controller(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getForum(@PathVariable("id") String id) {

        GetForumResponse forumResponse = queryBus.execute(
                new GetForumQuery(id)
        );

        return new ResponseEntity<>(Response.fromGetForumResponse(forumResponse), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Object> getForums() {

        GetForumsListResponse allForumsResponse = queryBus.execute(
                new GetAllForumsQuery()
        );

        return new ResponseEntity<>(allForumsResponse.stream().map(Response::fromGetForumResponse).collect(Collectors.toList()), HttpStatus.OK);

    }

    @ExceptionHandler({ForumNotFoundException.class})
    public ResponseEntity<Object> handleException() {
        return new ResponseEntity<>("Forum not found", HttpStatus.NOT_FOUND);
    }

}

@Data
@AllArgsConstructor
class Response {
    @NonNull
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private String icon;
    @NonNull
    private Integer numTopics;

    public static Response fromGetForumResponse(GetForumResponse getForumResponse) {
        return new Response(
                getForumResponse.getId(),
                getForumResponse.getName(),
                getForumResponse.getDescription(),
                getForumResponse.getIcon(),
                getForumResponse.getNumTopics()
        );
    }
}

