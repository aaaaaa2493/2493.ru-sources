package ru.vt.websockets.github.data.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vt.websockets.github.data.api.Event;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


public abstract sealed class GitHubEvent
        permits
        CommitCommentEvent, ForkEvent, IssueCommentEvent,
        IssuesEvent, PullRequestEvent, PullRequestReviewCommentEvent,
        PushEvent, ReleaseEvent, WikiPageEvent {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected final ObjectMapper mapper = new ObjectMapper();

    private final long id;

    @Getter
    String type;
    @Getter
    String owner;
    @Getter
    String repo;
    @Getter
    String url;

    @Getter
    @Setter
    @JsonIgnore
    Instant time;

    public GitHubEvent(Event event) throws IOException {
        this.type = event.getType().getName();
        this.id = event.getId();

        String repoName = event.getRepo().name();
        int slash = repoName.indexOf('/');

        owner = repoName.substring(0, slash);
        repo = repoName.substring(slash + 1);

        time = event.getCreatedAt()
                .plus(5, ChronoUnit.MINUTES)
                .plus(30, ChronoUnit.SECONDS);
    }

    // instead of making an additional request (using event.getRepository()) which lasts 0.3 sec
    // we can get repo name and owner of the repo instantly, though using somewhat a hack
//    protected void parseRepoAndOwnerFromUrl(String githubUrl) {
//        String initialPartCommit = "https://api.github.com/repos/";
//        String initialPartHttp = "https://github.com/";
//
//        if (githubUrl.startsWith(initialPartCommit)) {
//            githubUrl = githubUrl.substring(initialPartCommit.length());
//        } else if (githubUrl.startsWith(initialPartHttp)) {
//            githubUrl = githubUrl.substring(initialPartHttp.length());
//        } else {
//            repo = "?";
//            owner = "?";
//            return;
//        }
//
//        int firstSlash = githubUrl.indexOf('/');
//        int secondSlash = githubUrl.indexOf('/', firstSlash + 1);
//
//        owner = githubUrl.substring(0, firstSlash);
//
//        if (secondSlash > 0) {
//            repo = githubUrl.substring(firstSlash + 1, secondSlash);
//        } else {
//            repo = githubUrl.substring(firstSlash + 1);
//        }
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GitHubEvent that = (GitHubEvent) o;
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) this.id;
    }
}
