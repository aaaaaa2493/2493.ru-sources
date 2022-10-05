package ru.vt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vt.dto.SongCharts;
import ru.vt.entities.piudb.Mix.MixValues;
import ru.vt.services.SongService;

import java.util.List;

@Controller
@RequestMapping("piu")
public class PiuController {

    @Autowired
    SongService songService;

    @GetMapping
    public String menuPiu() {
        return "piu/menu";
    }

    @GetMapping("/random")
    public String randomizerPage(Model model) {
        model.addAttribute("rows", 5);
        model.addAttribute("columns", 5);
        model.addAttribute("width", 150);
        model.addAttribute("height", 105);

        List<SongCharts> xxSongs = songService.getAllSongsForMix(MixValues.XX.mixId);
        //xxSongs.stream().sorted().map(SongCharts::getPrint).forEach(System.out::println);

        model.addAttribute("songs", xxSongs);
        return "piu/randomizer";
    }

    @GetMapping("/stats")
    public String statsPage(Model model) {
        model.addAttribute("results", songService.getResults());
        return "piu/stats";
    }
}
