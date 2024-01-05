package ru.vt.entities.piudb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@Entity
public class Version implements Comparable<Version> {

    @Id
    int versionId;

    @ManyToOne
    Mix mix;

    @Column(name = "internalTitle")
    String name;

    @OneToOne
    @JoinColumn(name = "parentVersionId")
    @JsonIgnore
    Version parentVersion;

    int sortOrder;

    @Override
    public String toString() {
        if (name.equals("default")) {
            return mix + "";
        }
        return mix + " " + name;
    }

    public boolean isPrimeJEorInfinity() {
        return mix.mixId == 2 || mix.mixId == 8;
    }

    @Override
    public int compareTo(Version o) {
        return sortOrder - o.sortOrder;
    }
}
