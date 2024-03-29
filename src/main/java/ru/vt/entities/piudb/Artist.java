package ru.vt.entities.piudb;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Artist {

    @Id
    int artistId;

    @Column(name = "internalTitle")
    String name;

    @Override
    public String toString() {
        return name;
    }

}
