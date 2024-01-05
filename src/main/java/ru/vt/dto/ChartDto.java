package ru.vt.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.vt.entities.korean.ChartStyle;
import ru.vt.entities.piudb.Chart;
import ru.vt.entities.piudb.ChartDifficulty;
import ru.vt.entities.piudb.Mix;

import java.util.List;

@Data
public class ChartDto {
    @JsonIgnore
    final Chart chart;

    final int chartId;
    final List<String> stepmakers;
    final String mix;
    final ChartDifficulty difficulty;
    List<ChartStyle> styles;

    public ChartDto(Chart chart, Mix mix) {
        this.chart = chart;
        this.chartId = chart.getChartId();
        this.stepmakers = chart.getStepmakers();
        this.mix = chart.getMix();
        this.difficulty = chart.getDifficultyForMix(mix);
        this.styles = chart.getStyles();
    }
}
