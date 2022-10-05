package ru.vt.entities.piudb;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Game {

    @Id
    int gameId;

    @Column(name = "internalTitle")
    String name;

    public String toString() {
        return name;
    }

}
