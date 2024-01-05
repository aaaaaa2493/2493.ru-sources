package ru.vt.entities.piudb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OrderBy;
import ru.vt.entities.piudb.base.VersionsOperations;
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
import java.util.Comparator;
import java.util.List;

@Data
@Entity
public class Song implements VersionsOperations {

    @Id
    int songId;

    @ManyToOne
    Cut cut;

    public boolean isArcade() {
        return cut.isArcade();
    }

    public boolean isFullSong() {
        return cut.isFullSong();
    }

    public boolean isRemix() {
        return cut.isRemix();
    }

    public boolean isShortCut() {
        return cut.isShortCut();
    }

    @Column(name = "internalTitle")
    String name;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "chart")
    List<Chart> charts;

    public List<Chart> getCharts() {
        return charts.stream()
                .filter(Chart::hasCharts)
                .sorted()
                .toList();
    }

    public boolean hasCharts() {
        return !getCharts().isEmpty();
    }

    public List<Chart> getChartsForMix(Mix mix) {
        return getCharts().stream()
                .filter(c -> c.existInMix(mix))
                .toList();
    }

    public boolean hasChartsForMix(Mix mix) {
        return !getChartsForMix(mix).isEmpty();
    }

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy(clause = "sortOrder")
    List<Artist> artists;

    public List<String> getArtists() {
        return artists.stream().map(a -> a.name).toList();
    }

    @OneToMany
    @JoinTable(name = "songVersion")
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Version> versionsOperations;

    @OneToMany
    @JoinTable(name = "songVersion")
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Operation> operations;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "songBpm")
    List<SongBpm> bpm;

    @ManyToOne
    @JoinTable(name = "songCategory")
    Category category;

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

    public String getIdentifier() {
        if (gameIdentifier.size() == 1) {
            return gameIdentifier.get(0).gameIdentifier;
        } else if (gameIdentifier.size() > 1) {
            var sorted = gameIdentifier.stream().sorted(
                Comparator.comparing(SongGameIdentifier::lastAppearance)).toList();
            return sorted.get(sorted.size() - 1).gameIdentifier;
        }
        return null;
    }

    @OneToMany
    @JoinTable(name = "songCard", joinColumns = @JoinColumn(name = "songId"))
    @LazyCollection(LazyCollectionOption.FALSE)
    List<SongCard> card;

    public String getCard() {
        var sorted = card.stream().sorted().toList();
        return sorted.get(0).path;
    }

    public String getCardBig() {
        return "/img/card_big/" + getIdentifier() + ".png";
    }

    public List<SongCard> getAllCards() {
        return card.stream().sorted().toList();
    }

    @JsonProperty
    public SongBpm getBpm() {
        for (SongBpm b : bpm) {
            if (b.getBpmMin() == 0 && b.getBpmMax() == 0) {
                continue;
            }
            return b;
        }
        return bpm.get(0);
    }

    public String printForMix(Mix mix) {
        String song =  "Song(id=" + getSongId() +
            " | title=" + getName() +
            " | artists=" + Utils.join(", ", getArtists()) +
            " | category=" + getCategory() +
            " | mix=" + getStartingFromMix() +
            " | id=" + getIdentifier() +
            " | cut=" + getCut() +
            " | bpm=" + getBpm() +
            " | card=" + getCard() + ")";

        var charts = mix == null ? getCharts() : getChartsForMix(mix);

        String chartsStr = "";
        if (charts.size() != 0) {
            var mixPadding = charts.stream().mapToInt(e -> e.getMixInfo().length()).max().getAsInt();
            var stepmakersPadding = charts.stream().mapToInt(e -> e.getStepmakersStr().length()).max().getAsInt();
            Mix finalMix = mix;
            var stylesPadding = charts.stream().mapToInt(e -> e.getStylesStrForMix(finalMix).length()).max().getAsInt();

            chartsStr = Utils.join("\n",
                charts.stream().map(c -> c.printChartForMix(
                    finalMix, 5, 4, mixPadding, stepmakersPadding, stylesPadding)).toList()) + "\n";
        }

        if (mix == null) {
            mix = MixService.latestMix;
        }

        String chartsForMix = "charts for " + mix + " : " + getChartsForMix(mix).size();

        return song + "\n" + chartsStr + chartsForMix + "\n";
    }

    @Override
    public String toString() {
        return printForMix(null);
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
