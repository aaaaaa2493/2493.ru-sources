package ru.vt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vt.configuration.WebSocketConfiguration;

@Controller
@RequestMapping("/github")
public class GithubController {

    @Autowired
    WebSocketConfiguration webSocketConfiguration;

    @GetMapping
    public String getGithubPage(Model model) {
        model.addAttribute("wsPath",
                "ws://localhost:8080" + webSocketConfiguration.githubWsPath);
        return "github/frontend";
    }

}
