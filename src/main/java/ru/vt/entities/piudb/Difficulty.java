package ru.vt.entities.piudb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Difficulty {

    @Id
    int difficultyId;

    Integer value;

    int sortOrder;

    @Column(name = "internalTitle")
    String name;

    @JsonIgnore
    int danger;

    @JsonProperty
    public boolean isDanger() {
        return danger != 0;
    }

}
