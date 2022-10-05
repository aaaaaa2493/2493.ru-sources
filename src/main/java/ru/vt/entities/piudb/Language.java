package ru.vt.entities.piudb;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Language {

    @Id
    int languageId;

    String code;

    @Column(name = "internalTitle")
    String name;

}
