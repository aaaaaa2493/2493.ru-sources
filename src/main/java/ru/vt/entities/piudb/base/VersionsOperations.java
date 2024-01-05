package ru.vt.entities.piudb.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.vt.dto.MixFromTo;
import ru.vt.entities.piudb.ChartVersion;
import ru.vt.entities.piudb.Mix;
import ru.vt.entities.piudb.Operation;
import ru.vt.entities.piudb.Version;
import ru.vt.services.MixService;
import ru.vt.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public interface VersionsOperations {

    List<Version> getVersionsOperations();
    List<Operation> getOperations();

    @JsonIgnore
    default Version getStartingFromMix() {
        for (var cv: Inner.getChartVersions(this)) {
            if (cv.getOperation().isExist()) {
                return cv.getVersion();
            }
        }
        return null;
    }

    @JsonIgnore
    default Mix getMinMix() {
        var ranges = getChartExistMixRange();
        if (ranges.size() == 0) {
            return null;
        }
        return ranges.get(0).getFrom();
    }

    @JsonIgnore
    default Mix getMaxMix() {
        var ranges = getChartExistMixRange();
        if (ranges.size() == 0) {
            return null;
        }
        return ranges.get(ranges.size() - 1).getTo();
    }

    @JsonIgnore
    default boolean existInMix(Mix mix) {
        return Utils.any(getChartExistMixRange(), r -> r.between(mix));
    }

    @JsonIgnore
    default List<ChartVersion> getChartVersions() {
        return Inner.getChartVersions(this);
    }

    @JsonIgnore
    default List<MixFromTo> getChartExistMixRange() {
        return Inner.getChartExistMixRange(this);
    }

    @JsonIgnore
    default String getMixInfo() {
        var minMix = getMinMix();
        var maxMix = getMaxMix();

        if (minMix == null || maxMix == null) {
            return "???";
        }

        if (minMix.getMixId() == maxMix.getMixId()) {
            return minMix.toString();
        } else {
            return minMix + " - " + maxMix;
        }
    }

    class Inner {
        @JsonIgnore
        private static final Map<VersionsOperations, List<ChartVersion>> sortedChartVersionsMap = new HashMap<>();

        @JsonIgnore
        private static List<ChartVersion> getChartVersions(VersionsOperations vo) {
            var sortedChartVersions = sortedChartVersionsMap.computeIfAbsent(vo, k -> new ArrayList<>());

            Utils.synchronizeOnEmpty(sortedChartVersions, () -> {
                List<ChartVersion> chartVersions = new ArrayList<>();
                Iterator<Version> itVer = vo.getVersionsOperations().iterator();
                Iterator<Operation> itOp = vo.getOperations().iterator();

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
        private static final Map<VersionsOperations, List<MixFromTo>> chartExistMixRangeMap = new HashMap<>();

        @JsonIgnore
        private static List<MixFromTo> getChartExistMixRange(VersionsOperations vo) {
            var chartExistMix = chartExistMixRangeMap.computeIfAbsent(vo, k -> new ArrayList<>());

            Utils.synchronizeOnEmpty(chartExistMix, () -> {
                List<MixFromTo> versionFromTos = new ArrayList<>();

                Mix prevMix = null;
                Operation prevOperation = null;
                for (var version : getChartVersions(vo)) {
                    Mix mix = version.getVersion().getMix();
                    Operation operation = version.getOperation();

                    if (prevMix != null) {
                        if (operation.isDelete() && prevOperation != null) {
                            versionFromTos.add(new MixFromTo(prevMix, mix.getParentMix()));
                        }
                    }

                    prevMix = mix;
                    prevOperation = operation;
                }

                if (prevOperation != null && prevOperation.isExist()) {
                    versionFromTos.add(new MixFromTo(prevMix, MixService.latestMix));
                }

                chartExistMix.addAll(versionFromTos);
            });

            return chartExistMix;
        }

    }

}
