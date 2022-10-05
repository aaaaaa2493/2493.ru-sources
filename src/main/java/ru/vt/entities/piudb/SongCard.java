package ru.vt.entities.piudb;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class SongCard implements Comparable<SongCard> {

    @Id
    int songCardId;

    String path;

    int sortOrder;

    @Override
    public String toString() {
        return path;
    }

    @Override
    public int compareTo(SongCard o) {
        return sortOrder - o.sortOrder;
    }
}
