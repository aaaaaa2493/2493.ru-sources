package ru.vt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/random/menu")
    public String randomizerMenuPage() {
        return "piu/randomMenu";
    }

    @GetMapping("/random")
    public String bingoPage(Model model,
                            @RequestParam(required = false, defaultValue = "rigel") String skin) {

        model.addAttribute("rows", 5);
        model.addAttribute("columns", 5);
        model.addAttribute("width", 150);
        model.addAttribute("height", 105);

        List<SongCharts> xxSongs = songService.getAllSongsForMix(MixValues.XX.mixId);
        //xxSongs.stream().sorted().map(SongCharts::getPrint).forEach(System.out::println);
        model.addAttribute("songs", xxSongs);

        return switch (skin) {
            case "rigel" -> "piu/randomizer";
            case "yushka" -> "piu/randomizerMSK";
            default -> "piu/randomizer";
        };
    }

    @GetMapping("/randomizer")
    public String randomizerPage(Model model) {
        model.addAttribute("width", 150);
        model.addAttribute("height", 105);

        List<SongCharts> xxSongs = songService.getAllSongsForMix(MixValues.XX.mixId);
        //xxSongs.stream().sorted().map(SongCharts::getPrint).forEach(System.out::println);
        model.addAttribute("songs", xxSongs);

        return "piu/one_randomizer";
    }

    @GetMapping("/stats")
    public String statsPage(Model model) {
        model.addAttribute("results", songService.getResults());
        return "piu/stats";
    }
}
