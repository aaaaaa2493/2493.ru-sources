package ru.vt.entities.piudb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Data
@Entity
public class ChartRating implements Comparable<ChartRating> {

    @Id
    int chartRatingId;

    @ManyToOne
    Mode mode;

    @ManyToOne
    Difficulty difficulty;

    //@ManyToOne
    //@JoinTable(name = "chartRatingVersion",
    //        joinColumns = @JoinColumn(name = "chartRatingId"),
    //        inverseJoinColumns = @JoinColumn(name = "versionId"))
    @ManyToMany
    @JoinTable(name = "chartRatingVersion")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    List<Version> versions;

    @JsonProperty
    public List<Version> getVersions() {
        return versions.stream().filter(v -> !v.isPrimeJEorInfinity()).sorted().toList();
    }

    public String shortname() {
        return mode.abbreviation + (difficulty.value != null ? difficulty.value : difficulty.name);
    }

    @Override
    public String toString() {
        var vs = getVersions();
        String verInfo;

        if (vs.size() == 1) {
            verInfo = vs.get(0).toString();
        } else {
            verInfo = vs.toString();
        }

        return shortname() + " " + verInfo;
    }

    @Override
    public int compareTo(ChartRating o) {
        var sorted = getVersions();
        var oSorted = o.getVersions();

        var last = sorted.get(sorted.size() - 1);
        var oLast = oSorted.get(oSorted.size() - 1);

        return last.compareTo(oLast);
    }

}
