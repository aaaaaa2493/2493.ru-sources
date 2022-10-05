package ru.vt.entities.piudb;

import lombok.Data;

@Data
public class ChartDifficulty {
    String name;
    int difficulty;
    boolean isSingle;
    boolean isDouble;
    boolean isCoop;
    boolean isPerformance;

    public ChartDifficulty(String name,
                           int difficulty,
                           boolean isSingle,
                           boolean isDouble,
                           boolean isCoop,
                           boolean isPerformance) {
        this.name = name;
        this.difficulty = difficulty;
        this.isSingle = isSingle;
        this.isDouble = isDouble;
        this.isCoop = isCoop;
        this.isPerformance = isPerformance;
    }
}
