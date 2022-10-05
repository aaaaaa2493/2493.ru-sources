package ru.vt.entities.pumpking;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PumpkingResult {
    @JsonIgnore
    int id;
    @JsonIgnore
    String recognitionNotes; // result/machine_best
    String player;
    int rankMode;
    int score;
    String grade;
}
