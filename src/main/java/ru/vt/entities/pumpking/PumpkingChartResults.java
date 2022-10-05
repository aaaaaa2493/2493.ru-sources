package ru.vt.entities.pumpking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PumpkingChartResults implements Comparable<PumpkingChartResults> {
    List<PumpkingResult> results;
    @JsonProperty("bestGradeResults")
    List<PumpkingResult> bestGradeResults;
    PumpkingChart chart;


    @Override
    public int compareTo(PumpkingChartResults o) {
        return chart.trackName.compareTo(o.chart.trackName);
    }
}
