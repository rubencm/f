package net.rubencm.forum.forumsservice.domain.services.findforum;

import net.rubencm.forum.forumsservice.domain.aggregates.Forum;
import net.rubencm.forum.forumsservice.domain.repositories.Forums;
import net.rubencm.forum.shared.domain.valueobjects.ForumId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindForum {
    final private Forums forums;

    public FindForum(@Autowired Forums forums) {
        this.forums = forums;
    }

    public Forum execute(ForumId id) {
        Optional<Forum> forum = forums.byId(id);

        if (forum.isEmpty()) {
            throw new ForumNotFoundException();
        }

        return forum.get();
    }
}
