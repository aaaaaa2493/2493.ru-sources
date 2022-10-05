package ru.vt.entities.piudb;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Category {

    @Id
    int categoryId;

    @Column(name = "internalTitle")
    String name;

    int sortOrder;

    @Override
    public String toString() {
        return name;
    }

}
