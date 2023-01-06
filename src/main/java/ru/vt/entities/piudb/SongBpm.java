package ru.vt.entities.piudb;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class SongBpm {

    @Id
    int songBpmId;

    double bpmMin;
    double bpmMax;

    @Override
    public String toString() {
        if (bpmMin == 0 && bpmMax == 0) {
            return "???";
        }

        final String bpmMinStr;
        if (bpmMin == (int) bpmMin) {
            bpmMinStr = (int) bpmMin + "";
        } else {
            bpmMinStr = bpmMin + "";
        }

        final String bpmMaxStr;
        if (bpmMax == (int) bpmMax) {
            bpmMaxStr = (int) bpmMax + "";
        } else {
            bpmMaxStr = bpmMax + "";
        }

        if (bpmMin == bpmMax) {
            return bpmMaxStr;
        }
        return bpmMinStr + "~" + bpmMaxStr;
    }

}
