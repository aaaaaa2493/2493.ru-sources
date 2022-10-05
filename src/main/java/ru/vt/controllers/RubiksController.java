package ru.vt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rubiks")
public class RubiksController {

    @GetMapping
    public String rubiksCubePage() {
        return "rubiks/game";
    }

}
