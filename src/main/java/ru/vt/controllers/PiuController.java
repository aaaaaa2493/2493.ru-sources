package ru.vt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.vt.dto.ChartResultDto;
import ru.vt.dto.SongDto;
import ru.vt.entities.piudb.Mix.MixValues;
import ru.vt.services.MixService;
import ru.vt.services.PumpkingService;
import ru.vt.services.SongService;

import java.util.Arrays;
import java.util.List;

import static ru.vt.entities.piudb.Mix.MixValues.XX;

@Controller
@RequestMapping("piu")
public class PiuController {
    private final static String noValue = "NOVALUE";

    @Autowired
    SongService songService;

    @Autowired
    PumpkingService pumpkingService;

    @GetMapping
    public String menuPiu() {
        return "piu/menu";
    }

    @GetMapping("/random/menu")
    public String randomizerMenuPage() {
        return "piu/menuRandomizer";
    }

    @GetMapping("/random/songs")
    @ResponseBody
    public List<SongDto> getAllSongsForMix(@RequestParam(required = false, defaultValue = noValue) String mix) {
        if (MixValues.of(mix) == null) {
            return List.of();
        }
        return songService.getAllSongsForMix(MixValues.of(mix).mixId);
    }

    @GetMapping("/stats/results")
    @ResponseBody
    public List<ChartResultDto> getAllResultsForMix(@RequestParam(required = false, defaultValue = noValue) String mix) {
        if (MixValues.of(mix) == null) {
            return List.of();
        }
        return pumpkingService.getAllResultsForMix(MixValues.of(mix).mixId);
    }

    @GetMapping("/random")
    public String bingoPage(
            Model model,
            @RequestParam(required = false, defaultValue = "one_msk") String skin,
            @RequestParam(required = false, defaultValue = "5") int rows,
            @RequestParam(required = false, defaultValue = "5") int columns,
            @RequestParam(required = false, defaultValue = "150") int width,
            @RequestParam(required = false, defaultValue = "105") int height,
            @RequestParam(required = false, defaultValue = "true") boolean isBingo,
            @RequestParam(required = false, defaultValue = "false") boolean altLayout,
            @RequestParam(required = false, defaultValue = noValue) String mix
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

        model.addAttribute("songs", getAllSongsForMix(mix));
        model.addAttribute("mixes", Arrays.stream(MixValues.values()).map(e -> e.str).toList());
        model.addAttribute("defaultMix", MixService.latestMix.toString());

        model.addAttribute("skin", skin);

        return "piu/randomizer";
    }

    @GetMapping("/bingo")
    public String bingoPage() {
        return "forward:random?skin=msk";
    }

    @GetMapping("/randomizer")
    public String oneRandomPage() {
        return "forward:random?skin=one_msk";
    }

    @GetMapping("/stats")
    public String statsPage(Model model) {
        model.addAttribute("results", getAllResultsForMix(XX.str));
        model.addAttribute("latest", pumpkingService.getLatestResultsUpdate());
        return "piu/stats";
    }

    @GetMapping("/scoring")
    public String scoringPage() {
        return "piu/scoring";
    }
}
