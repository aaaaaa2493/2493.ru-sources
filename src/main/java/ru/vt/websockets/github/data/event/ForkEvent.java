package ru.vt.websockets.github.data.event;

import ru.vt.websockets.github.data.api.Event;

import java.io.IOException;

public final class ForkEvent extends GitHubEvent {
    public ForkEvent(Event event) throws IOException {
        super(event);
        var payload = event.getPayload();
        url = mapper.convertValue(payload.get("forkee").get("html_url"), String.class);
    }
}
