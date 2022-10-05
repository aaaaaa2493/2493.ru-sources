package ru.vt.entities.piudb;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Mode {

    @Id
    int modeId;

    @Column(name = "internalTitle")
    String name;

    @Column(name = "internalAbbreviation")
    String abbreviation;

    @Column(name = "internalHexColor")
    String hexColor;

    int padsUsed;

    int routine;
    int coOp;
    int performance;

    public boolean isRoutine() {
        return routine != 0;
    }

    public boolean isCoOp() {
        return coOp != 0;
    }

    public boolean isPerformance() {
        return performance != 0;
    }

    public boolean isSingle() {
        return modeId == 1 || modeId == 3;
    }

    public boolean isDouble() {
        return modeId == 2 || modeId == 4;
    }

}
