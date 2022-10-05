package ru.vt.entities.piudb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.vt.entities.piudb.Operation.OperationValues;
import ru.vt.services.MixService;
import ru.vt.utils.Utils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@Entity
public class Chart implements Comparable<Chart> {

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

    @JsonIgnore
    public List<ChartVersion> getChartVersions() {
        Utils.synchronizeOnEmpty(sortedChartVersions, () -> {
            List<ChartVersion> chartVersions = new ArrayList<>();
            Iterator<Version> itVer = versionsOperations.iterator();
            Iterator<Operation> itOp = operations.iterator();

            while (itVer.hasNext() && itOp.hasNext()) {
                var currVersion = itVer.next();
                var currOperation = itOp.next();

                // Prime JE and Infinity
                if (currVersion.isPrimeJEorInfinity()) {
                    continue;
                }

                chartVersions.add(new ChartVersion(currVersion, currOperation));
            }

            sortedChartVersions.addAll(chartVersions.stream().sorted().toList());
        });

        return sortedChartVersions;
    }

    @JsonIgnore
    public Mix getMinMix() {
        for (var cv: getChartVersions()) {
            if (cv.operation.operationId != OperationValues.DELETE.operationId) {
                return cv.version.mix;
            }
        }
        return null;
    }

    @JsonIgnore
    public Mix getMaxMix() {
        var versions = getChartVersions();
        if (versions.size() == 0) {
            return null;
        }

        var latestVersion = versions.get(versions.size() - 1);

        if (latestVersion.operation.operationId == OperationValues.DELETE.operationId) {
            return latestVersion.version.mix.parentMix;
        } else {
            // if last action is not DELETE then it's the latest Mix
            return MixService.latestMix;
        }
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
                diff.difficulty.value,
                diff.mode.isSingle(),
                diff.mode.isDouble(),
                diff.mode.isCoOp(),
                diff.mode.isPerformance());
    }

    public boolean hasCharts() {
        return !getChartRatings().isEmpty() && !getChartVersions().isEmpty();
    }

    public String toString() {
        var minMix = getMinMix();
        var maxMix = getMaxMix();
        String mixInfo;

        if (minMix.mixId == maxMix.mixId) {
            mixInfo = minMix.toString();
        } else {
            mixInfo = minMix + " - " + maxMix;
        }

        var lastDifficulty = getLastDifficulty().shortname();
        if (lastDifficulty.length() < 4) {
            lastDifficulty += " ".repeat(4 - lastDifficulty.length());
        }

        var stepmakersStr = String.join(", ", stepmakers.stream().map(Object::toString).toList());

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
