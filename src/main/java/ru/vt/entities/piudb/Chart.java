package ru.vt.entities.piudb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OrderBy;
import ru.vt.dto.ChartRatingFromTo;
import ru.vt.dto.ChartResults;
import ru.vt.dto.MixFromTo;
import ru.vt.entities.korean.ChartStyle;
import ru.vt.entities.piudb.base.VersionsOperations;
import ru.vt.services.MixService;
import ru.vt.utils.Utils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static ru.vt.utils.Utils.pad;

@Data
@Entity
public class Chart implements Comparable<Chart>, VersionsOperations {

    @Id
    int chartId;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy(clause = "sortOrder")
    @JsonIgnore
    List<Stepmaker> stepmakers;

    @JsonProperty
    public List<String> getStepmakers() {
        return stepmakers.stream().map(s -> s.name).toList();
    }

    @JsonIgnore
    public String getStepmakersStr() {
        return String.join(", ", stepmakers.stream().map(Object::toString).toList());
    }

    @OneToMany
    @JoinTable(name = "chartVersion")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    List<Version> versionsOperations;

    @OneToMany
    @JoinTable(name = "chartVersion")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    List<Operation> operations;

    @OneToMany
    @JoinTable(name = "chartRating")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    List<ChartRating> chartRatings;

    @Transient
    @JsonIgnore
    final List<ChartRatingFromTo> sortedChartRatings = new ArrayList<>();

    public List<ChartRatingFromTo> getChartRatings() {
        Utils.synchronizeOnEmpty(sortedChartRatings, () -> {
            class FlattenedChartRating implements Comparable<FlattenedChartRating> {
                final ChartRating chartRating;
                final Version version;

                public FlattenedChartRating(ChartRating chartRating, Version version) {
                    this.chartRating = chartRating;
                    this.version = version;
                }

                @Override
                public int compareTo(FlattenedChartRating o) {
                    return version.sortOrder - o.version.sortOrder;
                }
            }

            List<FlattenedChartRating> flattenedChartRatings = new ArrayList<>();

            for (var cr : chartRatings) {
                for (var ver : cr.getVersions()) {
                    flattenedChartRatings.add(new FlattenedChartRating(cr, ver));
                }
            }

            flattenedChartRatings = flattenedChartRatings.stream().sorted().toList();
            int size = flattenedChartRatings.size();

            List<ChartRatingFromTo> uncutResult = new ArrayList<>();

            Mix prevMix = null;
            FlattenedChartRating prevFcr = null;
            for (int i = 0; i < size; i++) {
                var fcr = flattenedChartRatings.get(i);
                var mix = fcr.version.mix;

                if (i + 1 < size && flattenedChartRatings.get(i + 1).version.mix == mix) {
                    continue;
                }

                if (prevMix != null) {
                    uncutResult.add(new ChartRatingFromTo(
                        new MixFromTo(prevMix, mix.getParentMix()),
                        prevFcr.chartRating
                    ));
                }

                if (prevMix == null && getMinMix() != null && mix.sortOrder > getMinMix().sortOrder) {
                    prevMix = getMinMix();
                } else {
                    prevMix = mix;
                }
                prevFcr = fcr;
            }

            if (prevMix != null) {
                uncutResult.add(new ChartRatingFromTo(
                    new MixFromTo(prevMix, getMaxMix()),
                    prevFcr.chartRating
                ));
            }

            sortedChartRatings.addAll(uncutResult);
        });

        return sortedChartRatings;
    }

    @Transient
    final List<ChartVersion> sortedChartVersions = new ArrayList<>();

    public String getMix() {
        return getMinMix().toString();
    }

    public ChartRating getLastDifficulty() {
        var crs = getChartRatings();
        return crs.get(crs.size() - 1).getChartRating();
    }

    public ChartDifficulty getDifficultyForMix(Mix mix) {
        for (var range : sortedChartRatings) {
            if (range.getMixRange().between(mix)) {
                var diff = range.getChartRating();
                return new ChartDifficulty(
                    diff.shortname(),
                    diff.difficulty != null && diff.difficulty.value != null ? diff.difficulty.value: 0,
                    diff.mode.isSingle(),
                    diff.mode.isDouble(),
                    diff.mode.isCoOp(),
                    diff.mode.isPerformance());
            }
        }
        throw new NoSuchElementException("No difficulty for chart " + chartId + " for mix " + mix);
    }

    public boolean hasCharts() {
        return !getChartRatings().isEmpty() && !getChartVersions().isEmpty();
    }

    @Transient
    ChartResults results;

    @Transient
    List<ChartStyle> styles = new ArrayList<>();

    public String getStylesStrForMix(Mix mix) {
        boolean isDouble;
        if (mix == null) {
            isDouble = getLastDifficulty().getMode().isDouble();
        } else {
            isDouble = getDifficultyForMix(mix).isDouble;
        }
        return Utils.join(", ", styles.stream().map(c -> c.str(isDouble)).toList());
    }

    public String printChartForMix(
        Mix mix, int idPad, int diffPad, int mixPad, int stepmakersPad, int stylesPad) {

        String diff;
        if (mix == null) {
            diff = getLastDifficulty().shortname();
        } else {
            diff = getDifficultyForMix(mix).getName();
        }

        var chartIdStr = pad(String.valueOf(chartId), idPad);
        var diffStr = pad(diff, diffPad);
        var mixInfo = pad(getMixInfo(), mixPad);
        var stepmakersStr = pad(getStepmakersStr(), stepmakersPad);
        var stylesStr = pad(getStylesStrForMix(mix), stylesPad);

        var str = chartIdStr + " | " + diffStr + " | " + mixInfo + " | " + stepmakersStr + " | " + stylesStr;

        return pad(str, 80) + "  "
            + String.join(" | ", getSortedChartRatings().stream().map(Object::toString).toList());
    }

    public String toString() {
        return printChartForMix(MixService.latestMix,5, 4, 0, 0, 0);
    }

    @Override
    public int compareTo(Chart o) {
        var diff = getLastDifficulty();
        var oDiff = o.getLastDifficulty();

        if (diff.mode != oDiff.mode) {
            return diff.mode.modeId - oDiff.mode.modeId;
        }

        return diff.difficulty.sortOrder - oDiff.difficulty.sortOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chart chart = (Chart) o;

        return chartId == chart.chartId;
    }

    @Override
    public int hashCode() {
        return chartId;
    }
}
