package hello;

import java.time.Instant;

public class Greeting {

    private final long id;
    private final String content;
    private final Instant timestamp;

    public Greeting(long id, String content, Instant timestamp) {
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTimestamp() {
        return timestamp.toString();
    }

}