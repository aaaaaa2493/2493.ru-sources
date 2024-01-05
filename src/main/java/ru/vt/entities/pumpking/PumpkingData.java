package ru.vt.entities.pumpking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class PumpkingData {
    Map<String, PumpkingChartResults> data;
    @JsonProperty("lastUpdatedOn") String lastUpdatedOn;
}
