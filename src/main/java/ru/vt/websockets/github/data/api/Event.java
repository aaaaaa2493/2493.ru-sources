package ru.vt.websockets.github.data.api;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.time.Instant;

@Data
public class Event {
    private long id;
    private EventType type;
    private Actor actor;
    private Repo repo;
    private Instant createdAt;
    private JsonNode payload;
    public record Actor(long id, String login) { }
    public record Repo(long id, String name) { }
}

