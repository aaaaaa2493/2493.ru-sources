package ru.vt.websockets.github.data.event;

import lombok.Getter;
import ru.vt.websockets.github.data.api.Event;

import java.io.IOException;

public final class PullRequestEvent extends GitHubEvent {
    @Getter
    final int commits;
    @Getter
    final String author;
    @Getter
    final String title;
    @Getter
    final int changedFiles;

    public PullRequestEvent(Event event) throws IOException {
        super(event);
        var payload = event.getPayload();

        var pr = payload.get("pull_request");

        commits = pr.get("commits").asInt();
        url = mapper.convertValue(pr.get("html_url"), String.class);
        author = event.getActor().login();
        title = mapper.convertValue(pr.get("title"), String.class);
        changedFiles = pr.get("changed_files").asInt();
    }
}
