package ru.vt.entities.piudb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class SongCard implements Comparable<SongCard> {

    @Id
    int songCardId;

    String path;

    @OneToMany
    @JoinTable(name = "songCardVersion")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    List<Version> versions;

    @OneToMany
    @JoinTable(name = "songCardVersion")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    List<Operation> operations;

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
