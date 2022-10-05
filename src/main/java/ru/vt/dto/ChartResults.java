package ru.vt.dto;

import lombok.Data;
import ru.vt.entities.piudb.Chart;
import ru.vt.entities.piudb.Song;
import ru.vt.entities.pumpking.PumpkingResult;

import java.util.List;

@Data
public class ChartResults {
    Song song;
    Chart chart;
    List<PumpkingResult> results;
    Double difficulty;

    public ChartResults(Song song, Chart chart, List<PumpkingResult> results, Double difficulty) {
        this.song = song;
        this.chart = chart;
        this.results = results;
        this.difficulty = difficulty;
    }
}
