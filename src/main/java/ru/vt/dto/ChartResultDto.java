package ru.vt.dto;

import lombok.Data;
import ru.vt.entities.piudb.Mix;
import ru.vt.entities.pumpking.PumpkingResult;

import java.util.List;

@Data
public class ChartResultDto {
    private final String title;
    private final String songCard;
    private final String pumpDifficulty;
    private final Double pumpkingDifficulty; // may be null
    private final List<PumpkingResult> results;

    public ChartResultDto(ChartResults result, Mix mix) {
        this.title = result.getSong().getName();
        this.songCard = result.getSong().getCard();
        this.pumpDifficulty = result.getChart().getDifficultyForMix(mix).getName();
        this.pumpkingDifficulty = result.getDifficulty();
        this.results = result.getResults();
    }
}
