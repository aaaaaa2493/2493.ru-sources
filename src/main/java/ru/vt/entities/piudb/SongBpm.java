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

}
