package ru.vt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/chess")
public class ChessController {

    @GetMapping
    public String chess() {
        return "chess/menu";
    }

    @GetMapping("/analysis")
    public String analysis() {
        return "chess/analysis";
    }

    @GetMapping("engine")
    public String gameWithEngine(@RequestParam int strength) {
        return "chess/engine";
    }

    @GetMapping("/engines")
    public String enginesMatch(@RequestParam int strength1, @RequestParam int strength2) {
        return "chess/engines";
    }

}
