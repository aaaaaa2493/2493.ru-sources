package ru.vt.websockets.github;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vt.configuration.WebSocketConfiguration;
import ru.vt.utils.Utils;
import ru.vt.websockets.github.data.api.Event;
import ru.vt.websockets.github.data.api.EventType;
import ru.vt.websockets.github.data.api.RateLimit;
import ru.vt.websockets.github.data.event.CommitCommentEvent;
import ru.vt.websockets.github.data.event.ForkEvent;
import ru.vt.websockets.github.data.event.GitHubEvent;
import ru.vt.websockets.github.data.event.IssueCommentEvent;
import ru.vt.websockets.github.data.event.IssuesEvent;
import ru.vt.websockets.github.data.event.PullRequestEvent;
import ru.vt.websockets.github.data.event.PullRequestReviewCommentEvent;
import ru.vt.websockets.github.data.event.PushEvent;
import ru.vt.websockets.github.data.event.ReleaseEvent;
import ru.vt.websockets.github.data.event.WikiPageEvent;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class GithubEventListener {
    private final Logger log = LoggerFactory.getLogger(getClass());

    ObjectMapper mapper = WebSocketConfiguration.objectMapper();

    private final List<Event> eventQueue = new ArrayList<>();
    private final List<GitHubEvent> listToSend = new ArrayList<>();

    //private GitHub gitHub;
    private final Consumer<GitHubEvent> broadcastEventFunc;
    private final String githubToken;

    public GithubEventListener(String githubToken, Consumer<GitHubEvent> broadcastEventFunc) {
        this.broadcastEventFunc = broadcastEventFunc;
        this.githubToken = githubToken;
        new Thread(this::downloadEvents).start();
        new Thread(this::handleEvents).start();
        new Thread(this::sendEvents).start();
    }

    private void removeDuplicates() {
        if (listToSend.size() < 2) {
            return;
        }

        while (true) {
            var last = listToSend.get(listToSend.size() - 1);

            // Basically the same as the loop below
            // listToSend.removeIf(i -> i.equals(last) && i != last);

            for (int i = listToSend.size() - 2; i >= 0; i--) {
                var curr = listToSend.get(i);
                if (last == curr) {
                    listToSend.remove(i);
                    log.debug("Deduplicated list i=" + i + " size=" + listToSend.size());
                    return;
                }
            }
        }
    }

    private void downloadEvents() {

        Event lastEvent = null;
        int secondsToSleep = 4;

        while (true) {
            try {
                List<Event> newEvents = new ArrayList<>();
                boolean needSkip = false;
                boolean needReduceSleep = false;
                boolean needIncreaseSleep = false;
                int skippedEvents = 0;

                List<Event> events = getEvents();

                for (Event event : events) {
                    if (!needSkip) {
                        if (lastEvent == null || event.getId() != lastEvent.getId()) {
                            newEvents.add(event);
                        } else {
                            needSkip = true;
                        }
                    } else {
                        skippedEvents++;
                    }
                }

                int newEventsSize = newEvents.size();
                if (newEventsSize > 0) {
                    if ((double) skippedEvents / newEventsSize < 0.4) {
                        needReduceSleep = true;
                    } else if ((double) skippedEvents / newEventsSize > 1) {
                        needIncreaseSleep = true;
                    }
                }

                if (needReduceSleep && secondsToSleep > 0) {
                    secondsToSleep--;
                }
                if (needIncreaseSleep) {
                    secondsToSleep++;
                }

                if (newEventsSize > 0) {
                    lastEvent = newEvents.get(0);
                }

                synchronized (eventQueue) {
                    eventQueue.addAll(0, newEvents);
                }

                synchronized (eventQueue) {
                    if (eventQueue.size() > 0) {
                        var pushes = eventQueue.stream()
                                .filter(e -> e.getType() == EventType.PushEvent).count();
                        if (false) {
                            log.debug(eventQueue.get(eventQueue.size() - 1).getCreatedAt() +
                                    " - " + eventQueue.get(0).getCreatedAt() +
                                    " events: " + eventQueue.size() +
                                    " pushes: " + pushes +
                                    " sleep: " + secondsToSleep +
                                    " limit: " + getRateLimit().getRate().remaining());
                        }
                    }
                }


            } catch (Exception e) {
                log.error("Error while downloading GitHub events", e);
            }

            try {
                Thread.sleep(1000L * secondsToSleep);
            } catch (InterruptedException e) {
                log.debug("Downloading INTERRUPTED");
                return;
            }
        }

    }

    private void handleEvents() {

        log.debug("Handle events started");

        while (true) {
            if (eventQueue.size() == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.debug("Handling INTERRUPTED");
                    return;
                }
                continue;
            }

            //synchronized (listToSend) {
            //    removeDuplicates();
            //}

            final Event event;
            synchronized (eventQueue) {
                event = eventQueue.remove(eventQueue.size() - 1);
            }



            try {
                //if (event.getRepository().isPrivate()) {
                //    log.debug("Found PRIVATE repo");
                //    continue;
                //}

                switch (event.getType()) {
                    case PushEvent -> {
                        if (event.getPayload().get("size").asInt() == 0) {
                            continue;
                        }
                        //log.debug("Found PUSH");
                        synchronized (listToSend) {
                            listToSend.add(new PushEvent(event));
                        }
                    }
                    case PullRequestEvent -> {
                        //log.debug("Found PULL REQUEST");
                        synchronized (listToSend) {
                            listToSend.add(new PullRequestEvent(event));
                        }
                    }
                    case CreateEvent -> {

                    }
                    case ForkEvent -> {
                        //log.debug("Found FORK");
                        synchronized (listToSend) {
                            listToSend.add(new ForkEvent(event));
                        }
                    }
                    case WatchEvent -> {
                        //log.debug("Found WATCH");
                    }
                    case IssuesEvent -> {
                        //log.debug("Found ISSUE");
                        synchronized (listToSend) {
                            listToSend.add(new IssuesEvent(event));
                        }
                    }
                    case DeleteEvent -> {
                        //log.debug("Found DELETE");
                    }
                    case IssueCommentEvent -> {
                        //log.debug("Found ISSUE COMMENT");
                        synchronized (listToSend) {
                            listToSend.add(new IssueCommentEvent(event));
                        }
                    }
                    case PullRequestReviewCommentEvent -> {
                        //log.debug("Found PULL REQUEST REVIEW COMMENT");
                        synchronized (listToSend) {
                            listToSend.add(new PullRequestReviewCommentEvent(event));
                        }
                    }
                    case GollumEvent -> {
                        //log.debug("Found WIKI EDIT");
                        var payload = event.getPayload();

                        List<JsonNode> pages = Utils.toArrayList(payload.get("pages").elements());

                        if (payload.get("pages").size() == 0) {
                            continue;
                        }

                        synchronized (listToSend) {
                            listToSend.add(new WikiPageEvent(event, pages));
                        }
                    }
                    case ReleaseEvent -> {
                        //log.debug("Found RELEASE");
                        synchronized (listToSend) {
                            listToSend.add(new ReleaseEvent(event));
                        }
                    }
                    case CommitCommentEvent -> {
                        //log.debug("Found COMMIT COMMENT");
                        synchronized (listToSend) {
                            listToSend.add(new CommitCommentEvent(event));
                        }
                    }
                    case MemberEvent -> {
                        //log.debug("Found MEMBER");
                    }
                    default -> {
                        //log.debug("Found DEFAULT " + event.getType().name());
                    }
                }
            } catch (IOException e) {
                log.debug("Error parsing event", e);
            }

        }

    }

    private void sendEvents() {
        log.debug("Send events started");

        while (true) {

            synchronized (listToSend) {
                for (var item : listToSend) {
                    var curTime = item.getTime();
                    var sameTimeList = listToSend.stream()
                            .filter(i -> i.getTime().equals(curTime)).toList();
                    if (sameTimeList.size() > 1) {
                        int divider = sameTimeList.size();

                        for (var curr : Utils.enumerate(sameTimeList)) {
                            int increaseMs = curr.index * 1000 / divider;

                            curr.item.setTime(curTime.plus(increaseMs, ChronoUnit.MILLIS));
                        }
                    }
                }
            }

            var now = Instant.now();

            List<GitHubEvent> toSendNow;
            synchronized (listToSend) {
                toSendNow = listToSend.stream()
                        .filter(i -> i.getTime().isBefore(now)).toList();
                listToSend.removeIf(i -> i.getTime().isBefore(now));
            }

            for (var sendItem : toSendNow) {
                broadcastEventFunc.accept(sendItem);
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                log.debug("Sending INTERRUPTED");
                return;
            }

        }
    }

    private String getResponse(String path) throws Exception {
        var request = HttpRequest.newBuilder()
            .uri(new URI("https://api.github.com" + path))
            .header("Authorization", "Bearer " + githubToken)
            .GET()
            .build();
        return  HttpClient.newBuilder().build().send(request, BodyHandlers.ofString()).body();
    }

    private RateLimit getRateLimit() throws Exception {
        return mapper.readValue(getResponse("/rate_limit"), RateLimit.class);
    }

    private List<Event> getEvents() throws Exception {
        return  Arrays.asList(mapper.readValue(getResponse("/events"), Event[].class));
    }

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.github.com/events"))
                .header("Authorization", "Bearer ")
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newBuilder()
                .build().send(request, BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<Event> obj = Arrays.asList(mapper.readValue(response.body(), Event[].class));

        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
        //System.out.println(rateLimit.rate().limit());


    }

}
