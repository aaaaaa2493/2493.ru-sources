package ru.vt.entities.piudb;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class SongGameIdentifier {

    @Id
    int songGameIdentifierId;

    String gameIdentifier;

    @Override
    public String toString() {
        return gameIdentifier;
    }

}
