package ru.vt.entities.korean;

import lombok.Data;

import java.util.List;

@Data
public class KoreanChartData {
    String difficulty;
    List<ChartStyle> styles;

    public KoreanChartData(String difficulty, List<ChartStyle> styles) {
        this.difficulty = difficulty;
        this.styles = styles;
    }
}
