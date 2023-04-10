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
        return "piu/menuRandomizer";
    }

    @GetMapping("/random")
    public String bingoPage(
            Model model,
            @RequestParam(required = false, defaultValue = "rigel") String skin,
            @RequestParam(required = false, defaultValue = "5") int rows,
            @RequestParam(required = false, defaultValue = "5") int columns,
            @RequestParam(required = false, defaultValue = "150") int width,
            @RequestParam(required = false, defaultValue = "105") int height,
            @RequestParam(required = false, defaultValue = "true") boolean isBingo,
            @RequestParam(required = false, defaultValue = "false") boolean altLayout
    ) {
        if (skin.startsWith("one")) {
            rows = 1;
            columns = 1;
            isBingo = false;
            if (skin.equals("one_msk")) {
                altLayout = true;
            }
        }

        model.addAttribute("rows", rows);
        model.addAttribute("columns", columns);
        model.addAttribute("width", width);
        model.addAttribute("height", height);

        model.addAttribute("isBingo", isBingo);
        model.addAttribute("altLayout", altLayout);

        List<SongCharts> xxSongs = songService.getAllSongsForMix(MixValues.XX.mixId);
        //xxSongs.stream().sorted().map(SongCharts::getPrint).forEach(System.out::println);
        model.addAttribute("songs", xxSongs);

        model.addAttribute("skin", skin);

        return "piu/randomizer";
    }

    @GetMapping("/randomizer")
    public String oneRandomPage() {
        return "forward:random?skin=one_spb";
    }

    @GetMapping("/stats")
    public String statsPage(Model model) {
        model.addAttribute("results", songService.getResults());
        return "piu/stats";
    }
}
