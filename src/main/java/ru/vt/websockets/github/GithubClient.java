package ru.vt.websockets.github;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.vt.configuration.WebSocketConfiguration;
import ru.vt.websockets.github.data.event.GitHubEvent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static ru.vt.websockets.github.data.api.EventType.CommitCommentEvent;
import static ru.vt.websockets.github.data.api.EventType.ForkEvent;
import static ru.vt.websockets.github.data.api.EventType.GollumEvent;
import static ru.vt.websockets.github.data.api.EventType.IssueCommentEvent;
import static ru.vt.websockets.github.data.api.EventType.IssuesEvent;
import static ru.vt.websockets.github.data.api.EventType.PullRequestEvent;
import static ru.vt.websockets.github.data.api.EventType.PullRequestReviewCommentEvent;
import static ru.vt.websockets.github.data.api.EventType.PushEvent;
import static ru.vt.websockets.github.data.api.EventType.ReleaseEvent;

public class GithubClient {

    ObjectMapper mapper = WebSocketConfiguration.objectMapper();

    @Data
    static class Type {
        String type;
    }

    @Data
    static class RegexpFilters {
        String owner;
        String repo;
    }

    @Data
    static class TypeFilters {
        boolean pullRequest;
        boolean push;
        boolean issue;
        boolean forkRepo;
        boolean wikiPage;
        boolean release;
        boolean pullRequestReview;
        boolean commitComment;
        boolean issueComment;
    }

    private static class FilterError {
        String type = "error";
        String error;


        public FilterError(String error) {
            this.error = error;
        }
    }

    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final Map<String, Integer> audioLengths = Map.of(
            "Guitar", 57,
            "Saxophone", 37,
            "stage1", 22,
            "stage2", 67,
            "Viola", 65
    );

    private final Map<String, Boolean> typeFilters = new HashMap<>(Map.of(
            PullRequestEvent.getName(), false,
            PushEvent.getName(), false,
            IssuesEvent.getName(), false,
            ForkEvent.getName(), false,
            GollumEvent.getName(), false,
            ReleaseEvent.getName(), false,
            PullRequestReviewCommentEvent.getName(), false,
            CommitCommentEvent.getName(), false,
            IssueCommentEvent.getName(), false
    ));

    private String id;
    private WebSocketSession handler;
    private Pattern ownerFilter = Pattern.compile("");
    private Pattern repoFilter = Pattern.compile("");

    public GithubClient(WebSocketSession client) {
        id = client.getId();
        handler = client;
        send(Map.of("type", "init", "categories", audioLengths));
    }

    public void send(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            handler.sendMessage(new TextMessage(mapper.writeValueAsString(object)));
        } catch (JsonProcessingException e) {
            log.error("Can't turn Object into JSON. Client " + id, e);
        } catch (IOException e) {
            log.error("Can't send message to client " + id, e);
        }
    }

    public void receive(GithubSocket srv, String message) {
        try {
            String type = mapper.readValue(message, Type.class).type;
            if (type.equals("filter_regexp")) {
                setRegexpFilters(mapper.readValue(message, RegexpFilters.class));
            } else if (type.equals("filter_types")) {
                setTypeFilters(mapper.readValue(message, TypeFilters.class));
            }
        } catch (JsonProcessingException e) {
            log.debug("Can't decode JSON", e);
        }
    }

    private void setRegexpFilters(RegexpFilters filters) {
        try {
            String owner = filters.owner.toLowerCase();
            if (owner.length() == 0) {
                owner = ".*";
            }
            ownerFilter = Pattern.compile(owner, Pattern.CASE_INSENSITIVE);
        } catch (Exception e) {
            send(new FilterError("owner"));
        }

        try {
            String repo = filters.repo.toLowerCase();
            if (repo.length() == 0) {
                repo = ".*";
            }
            repoFilter = Pattern.compile(repo, Pattern.CASE_INSENSITIVE);
        } catch (Exception e) {
            send(new FilterError("repo"));
        }
    }

    private void setTypeFilters(TypeFilters filters) {
        typeFilters.put(PullRequestEvent.getName(), filters.pullRequest);
        typeFilters.put(PushEvent.getName(), filters.push);
        typeFilters.put(IssuesEvent.getName(), filters.issue);
        typeFilters.put(ForkEvent.getName(), filters.forkRepo);
        typeFilters.put(GollumEvent.getName(), filters.wikiPage);
        typeFilters.put(ReleaseEvent.getName(), filters.release);
        typeFilters.put(PullRequestReviewCommentEvent.getName(), filters.pullRequestReview);
        typeFilters.put(CommitCommentEvent.getName(), filters.commitComment);
        typeFilters.put(IssueCommentEvent.getName(), filters.issueComment);
    }

    public boolean passFilters(GitHubEvent event) {
        return passTypeFilters(event) && passRegexpFilters(event);
    }

    private boolean passRegexpFilters(GitHubEvent event) {
        boolean ownerMatch = ownerFilter.matcher(event.getOwner()).matches();
        boolean repoMatch = repoFilter.matcher(event.getRepo()).matches();
        return ownerMatch && repoMatch;
    }

    private boolean passTypeFilters(GitHubEvent event) {
        return typeFilters.get(event.getType());
    }

}
