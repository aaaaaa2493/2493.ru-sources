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
    public String bingoPage(
            Model model,
            @RequestParam(required = false, defaultValue = "rigel") String skin,
            @RequestParam(required = false, defaultValue = "5") int rows,
            @RequestParam(required = false, defaultValue = "5") int columns,
            @RequestParam(required = false, defaultValue = "150") int width,
            @RequestParam(required = false, defaultValue = "105") int height
    ) {
        if (skin.equals("one")) {
            rows = 1;
            columns = 1;
        }

        model.addAttribute("rows", rows);
        model.addAttribute("columns", columns);
        model.addAttribute("width", width);
        model.addAttribute("height", height);

        List<SongCharts> xxSongs = songService.getAllSongsForMix(MixValues.XX.mixId);
        //xxSongs.stream().sorted().map(SongCharts::getPrint).forEach(System.out::println);
        model.addAttribute("songs", xxSongs);

        model.addAttribute("skin", skin);

        return "piu/randomizer";
    }

    @GetMapping("/stats")
    public String statsPage(Model model) {
        model.addAttribute("results", songService.getResults());
        return "piu/stats";
    }
}
