package ru.vt.dto;

import lombok.Data;
import ru.vt.entities.piudb.ChartRating;

@Data
public class ChartRatingFromTo {
    MixFromTo mixRange;
    ChartRating chartRating;

    public ChartRatingFromTo(MixFromTo mixRange, ChartRating chartRating) {
        this.mixRange = mixRange;
        this.chartRating = chartRating;
    }

    @Override
    public String toString() {
        return chartRating.shortnamePadded(3) + " - " + mixRange.from;
    }
}
