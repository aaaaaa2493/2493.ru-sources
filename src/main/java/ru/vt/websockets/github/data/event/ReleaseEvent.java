package ru.vt.websockets.github.data.event;

import ru.vt.websockets.github.data.api.Event;

import java.io.IOException;

public final class ReleaseEvent extends GitHubEvent {
    public ReleaseEvent(Event event) throws IOException {
        super(event);
        var payload = event.getPayload();
        url = mapper.convertValue(payload.get("release").get("html_url"), String.class);
    }
}
