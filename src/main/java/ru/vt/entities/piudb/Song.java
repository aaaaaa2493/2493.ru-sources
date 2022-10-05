package ru.vt.entities.piudb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.vt.services.MixService;
import ru.vt.utils.Utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@Entity
public class Song {

    @Id
    int songId;

    @OneToOne
    @JsonIgnore
    Cut cut;

    @JsonProperty
    public String getCut() {
        return cut.name;
    }

    @JsonIgnore
    public int getCutId() {
        return cut.cutId;
    }

    @Column(name = "internalTitle")
    String name;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "chart")
    @JsonIgnore
    List<Chart> charts;

    public List<Chart> getCharts() {
        return charts.stream()
                .filter(Chart::hasCharts)
                .sorted()
                .toList();
    }

    public List<Chart> getChartsForMix(Mix mix) {
        return getCharts().stream()
                .filter(c -> c.getMinMix().sortOrder <= mix.sortOrder
                        && c.getMaxMix().sortOrder >= mix.sortOrder)
                .toList();
    }

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    List<Artist> artists;

    @JsonProperty
    public List<String> getArtists() {
        return artists.stream().map(a -> a.name).toList();
    }

    @OneToOne
    @JoinTable(name = "songBpm")
    @JsonIgnore
    SongBpm bpm;

    @ManyToOne
    @JoinTable(name = "songCategory")
    @JsonIgnore
    Category category;

    @JsonProperty
    public String getCategory() {
        return category.name;
    }

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "songTitle")
    @JsonIgnore
    List<SongTitle> songTitles;

    @OneToMany
    @JoinTable(name = "songGameIdentifier")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    List<SongGameIdentifier> gameIdentifier;

    @JsonProperty
    public String getIdentifier() {
        return gameIdentifier.get(gameIdentifier.size() - 1).gameIdentifier;
    }

    @OneToMany
    @JoinTable(name = "songCard", joinColumns = @JoinColumn(name = "songId"))
    @LazyCollection(LazyCollectionOption.FALSE)
    List<SongCard> card;

    @JsonProperty
    public String getCard() {
        var sorted = card.stream().sorted().toList();
        return sorted.get(0).path;
    }

    @JsonProperty
    public int getBpm() {
        return (int) ((bpm.getBpmMax() + bpm.getBpmMin()) / 2);
    }

    @Override
    public String toString() {
        String song =  "Song(id=" + getSongId() +
                " | title=" + getName() +
                " | artists=" + Utils.join(", ", getArtists()) +
                " | category=" + getCategory() +
                " | id=" + getIdentifier() +
                " | cut=" + getCut() +
                " | bpm=" + getBpm() +
                " | card=" + getCard() + ")";

        String charts = Utils.join("\n", getCharts());

        Mix latest = MixService.latestMix;
        String chartsForMix = "charts for " + latest + " : " + getChartsForMix(latest).size();

        return song + "\n" + charts + "\n" + chartsForMix + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        return songId == song.songId;
    }

    @Override
    public int hashCode() {
        return songId;
    }
}
