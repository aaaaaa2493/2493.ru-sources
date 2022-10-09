package ru.vt.websockets.github;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.vt.websockets.github.data.event.GitHubEvent;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class GithubSocket extends TextWebSocketHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();
    private final Map<WebSocketSession, GithubClient> clients = new ConcurrentHashMap<>();

    public GithubSocket(String githubToken) {
        GithubEventListener eventListener = new GithubEventListener(githubToken, this::broadcast);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        clients.put(session, new GithubClient(session));
        log.debug("New client connected and was given id " + session.getId());
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        clients.remove(session);
        log.debug("Client " + session.getId() + " disconnected");
        super.afterConnectionClosed(session, status);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        System.out.println("SOCKET MESSAGE: " + message.getPayload());
        clients.get(session).receive(this, message.getPayload());
    }

    protected void broadcast(GitHubEvent event) {
        for (GithubClient client : clients.values()) {
            if (client.passFilters(event)) {
                client.send(event);
            }
        }
    }

}
