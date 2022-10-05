package ru.vt.entities.pumpking;

import lombok.Data;

@Data
public class PumpkingChart {
    String trackName;
    String chartLabel;
    int level;
    Double difficulty;
    PumpkingDuration duration;
}
