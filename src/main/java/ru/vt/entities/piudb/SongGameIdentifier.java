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
public class SongGameIdentifier {

    @Id
    int songGameIdentifierId;

    String gameIdentifier;

    @OneToMany
    @JoinTable(name = "songGameIdentifierVersion")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    List<Version> versions;

    @OneToMany
    @JoinTable(name = "songGameIdentifierVersion")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    List<Operation> operations;

    public Version lastAppearance() {
        var latestVersion = versions.get(versions.size() - 1);
        if (operations.get(operations.size() - 1).isExist()) {
            return latestVersion;
        } else {
            return latestVersion.parentVersion;
        }
    }

    @Override
    public String toString() {
        return gameIdentifier;
    }

}
