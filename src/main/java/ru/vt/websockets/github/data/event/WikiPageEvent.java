package ru.vt.websockets.github.data.event;

import com.fasterxml.jackson.databind.JsonNode;
import ru.vt.websockets.github.data.api.Event;

import java.io.IOException;
import java.util.List;

public final class WikiPageEvent extends GitHubEvent {
    public WikiPageEvent(Event event, List<JsonNode> pages) throws IOException {
        super(event);
        var last = pages.get(pages.size() - 1);
        url = mapper.convertValue(last.get("html_url"), String.class);
    }
}
