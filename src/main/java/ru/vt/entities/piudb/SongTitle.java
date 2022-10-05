package ru.vt.entities.piudb;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class SongTitle {

    @Id
    int songTitleId;

    @ManyToOne
    Language language;

    String title;

}
