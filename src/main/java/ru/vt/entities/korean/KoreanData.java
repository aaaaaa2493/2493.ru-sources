package ru.vt.entities.korean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.vt.entities.pumpking.PumpkingChartResults;

import java.util.Map;

@Data
public class KoreanData {
    Map<String, PumpkingChartResults> data;
    String lastUpdateOn;
    @JsonIgnore
    Map<String, String> idToName;
}
