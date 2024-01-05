package ru.vt.configuration;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class DomainInterceptor implements HandlerInterceptor {

    private final static String HOST_2493 = "2493.ru";
    private final static String HOST_PIURANDOM = "piurandom.com";
    private final static String ATTR_IS_FORWARDED = "isForwarded";

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        String host = request.getHeader("Host");
        String path = request.getRequestURI();

        if (path.equals("/favicon.ico")) {
            if (HOST_2493.equals(host) || HOST_PIURANDOM.equals(host)) {
                request.getRequestDispatcher("/" + host + ".ico").forward(request, response);
            } else {
                request.getRequestDispatcher("/" + HOST_2493 + ".ico").forward(request, response);
            }
            return false;
        }

        if (!"/error".equals(path)
            && !path.endsWith(".html")
            && !path.endsWith(".css")
            && !path.endsWith(".js")
            && !path.endsWith(".mp3")
            && !path.endsWith(".png")
            && !path.endsWith(".svg")
            && !path.endsWith(".ico")
        ) {
            if (HOST_PIURANDOM.equals(host)) {
                if ("/".equals(path)) {
                    request.setAttribute(ATTR_IS_FORWARDED, true);
                    request.getRequestDispatcher("/piu/random").forward(request, response);
                    return false;
                } else if ("/piu/random".equals(path)) {
                    Boolean isForwarded = (Boolean) request.getAttribute(ATTR_IS_FORWARDED);
                    if (isForwarded == null || !isForwarded) {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                        return false;
                    }
                } else if (!path.startsWith("/piu/random/songs")) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return false;
                }
            }
        }

        return true;
    }
}
