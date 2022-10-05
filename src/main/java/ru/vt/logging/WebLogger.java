package ru.vt.logging;

import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

public class WebLogger extends CommonsRequestLoggingFilter {

    public WebLogger() {
        setBeforeMessagePrefix("");
        setBeforeMessageSuffix("");
        setIncludeQueryString(true);
        setIncludePayload(true);
        setMaxPayloadLength(10000);
        setIncludeHeaders(false);
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        if (message.endsWith(".png")
                || message.endsWith(".css")
                || message.endsWith(".js")
                || message.endsWith(".ico")) {
            return;
        }
        logger.debug(message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        // logger.debug(message);
    }

}
