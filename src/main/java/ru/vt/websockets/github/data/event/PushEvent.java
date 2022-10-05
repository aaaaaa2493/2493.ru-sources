package ru.vt.websockets.github.data.event;

import lombok.Getter;
import ru.vt.websockets.github.data.api.Event;

import java.io.IOException;

public final class PushEvent extends GitHubEvent {
    @Getter
    final int commits;
    @Getter
    final String hash;

    public PushEvent(Event event) throws IOException {
        super(event);

        var payload = event.getPayload();

        commits = payload.get("size").asInt();
        hash = mapper.convertValue(payload.get("head"), String.class);

        url = "https://github.com/" + owner + "/" + repo + "/commit/" + hash;
    }


}
