package ru.vt.entities.piudb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class SongBpm {

    @Id
    @JsonIgnore
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
            bpmMinStr = String.valueOf((int) bpmMin);
        } else {
            bpmMinStr = String.valueOf(bpmMin);
        }

        final String bpmMaxStr;
        if (bpmMax == (int) bpmMax) {
            bpmMaxStr = String.valueOf((int) bpmMax);
        } else {
            bpmMaxStr = String.valueOf(bpmMax);
        }

        if (bpmMin == bpmMax) {
            return bpmMaxStr;
        }
        return bpmMinStr + "~" + bpmMaxStr;
    }

}
