package ru.vt.entities.piudb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.vt.entities.piudb.base.VersionsOperations;
import ru.vt.utils.Utils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Chart implements Comparable<Chart>, VersionsOperations {

    @Id
    int chartId;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    List<Stepmaker> stepmakers;

    @JsonProperty
    public List<String> getStepmakers() {
        return stepmakers.stream().map(s -> s.name).toList();
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
    final List<ChartRating> sortedChartRatings = new ArrayList<>();

    @JsonIgnore
    public List<ChartRating> getChartRatings() {
        Utils.synchronizeOnEmpty(sortedChartRatings, () -> sortedChartRatings.addAll(
                chartRatings.stream().filter(r -> !r.getVersions().isEmpty()).sorted().toList()));
        return sortedChartRatings;
    }

    @Transient
    @JsonIgnore
    final List<ChartVersion> sortedChartVersions = new ArrayList<>();

    public String getMix() {
        return getMinMix().toString();
    }

    @JsonIgnore
    public ChartRating getLastDifficulty() {
        var crs = getChartRatings();
        return crs.get(crs.size() - 1);
    }

    public ChartDifficulty getDifficulty() {
        var diff = getLastDifficulty();
        return new ChartDifficulty(
                diff.shortname(),
                diff.difficulty != null? diff.difficulty.value: 0,
                diff.mode.isSingle(),
                diff.mode.isDouble(),
                diff.mode.isCoOp(),
                diff.mode.isPerformance());
    }

    public boolean hasCharts() {
        return !getChartRatings().isEmpty() && !getChartVersions().isEmpty();
    }

    public String toString() {
        String mixInfo = getMixInfo();

        var lastDifficulty = getLastDifficulty().shortname();
        if (lastDifficulty.length() < 4) {
            lastDifficulty += " ".repeat(4 - lastDifficulty.length());
        }

        var stepmakersStr = String.join(", ", stepmakers.stream().map(Object::toString).toList());

        var chartIdStr = chartId + "";
        if (chartIdStr.length() < 5) {
            chartIdStr += " ".repeat(5 - chartIdStr.length());
        }

        return lastDifficulty + " | " + mixInfo + " | " + stepmakersStr;
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
