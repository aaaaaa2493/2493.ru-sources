package ru.vt.websockets.github.data.api;

import lombok.Getter;

public enum EventType {
    PushEvent("push"),
    CommitCommentEvent("commit_comment"),
    PullRequestEvent("pull_request"),
    PullRequestReviewEvent(""),
    PullRequestReviewCommentEvent("pull_request_review"),
    IssuesEvent("issue"),
    IssueCommentEvent("issue_comment"),
    CreateEvent(""),
    DeleteEvent(""),
    MemberEvent(""),
    WatchEvent(""),
    ReleaseEvent("release"),
    PublicEvent(""),
    ForkEvent("fork_repo"),
    GollumEvent("wiki_page");

    @Getter
    final String name;

    EventType(String name) {
        this.name = name;
    }
}
