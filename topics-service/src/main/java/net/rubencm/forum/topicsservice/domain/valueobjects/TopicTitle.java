package net.rubencm.forum.topicsservice.domain.valueobjects;

public class TopicTitle {
    protected String value;

    public TopicTitle(String value) {
        this.validateLength(value);
        this.value = value;
    }

    public TopicTitle() {
    }

    public String value() {
        return this.value;
    }

    private void validateLength(String value) {
        Integer length = value.length();
        if (length < 5 || length > 100) {
            throw new IllegalArgumentException("Value must have a length between 5 and 100 characters.");
        }
    }
}
