package ru.vt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tictactoe")
public class TictactoeController {

    @GetMapping
    public String tictactoePage() {
        return "tictactoe/game";
    }

}
