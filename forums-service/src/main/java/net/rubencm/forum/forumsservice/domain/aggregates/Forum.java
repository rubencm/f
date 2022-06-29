package net.rubencm.forum.forumsservice.domain.aggregates;

import lombok.*;
import net.rubencm.forum.forumsservice.domain.valueobjects.ForumDescription;
import net.rubencm.forum.forumsservice.domain.valueobjects.ForumIcon;
import net.rubencm.forum.forumsservice.domain.valueobjects.ForumName;
import net.rubencm.forum.forumsservice.domain.valueobjects.NumberTopics;
import net.rubencm.forum.shared.domain.Aggregate;
import net.rubencm.forum.shared.domain.valueobjects.ForumId;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class Forum extends Aggregate {
    @NonNull
    private ForumId id;
    @NonNull
    private ForumName name;
    @NonNull
    private ForumDescription description;
    @NonNull
    private ForumIcon icon;
    @NonNull
    private NumberTopics numTopics;

    public Forum(@NonNull ForumId id, @NonNull ForumName name, @NonNull ForumDescription description, @NonNull ForumIcon icon, @NonNull NumberTopics numTopics) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.numTopics = numTopics;
    }

    public static Forum create(ForumId forumId, ForumName name, ForumDescription description, ForumIcon icon) {
        return new Forum(forumId, name, description, icon, new NumberTopics(0));
    }

    public void increaseTopics() {
        this.numTopics = new NumberTopics(this.numTopics.value() + 1);
    }

    public void decreaseTopics() {
        this.numTopics = new NumberTopics(this.numTopics.value() - 1);
    }
}
