package ru.vt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vt.configuration.WebSocketConfiguration;

@Controller
@RequestMapping("/github")
public class GithubController {

    @Value("${server.port}")
    int port;

    @Autowired
    WebSocketConfiguration webSocketConfiguration;

    @GetMapping
    public String getGithubPage(Model model) {
        model.addAttribute("wsPath",
                "ws://2493.ru:" + port + webSocketConfiguration.githubWsPath);
        return "github/frontend";
    }

}
