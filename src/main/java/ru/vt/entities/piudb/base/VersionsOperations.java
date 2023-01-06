package ru.vt.entities.piudb.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.vt.entities.piudb.ChartVersion;
import ru.vt.entities.piudb.Mix;
import ru.vt.entities.piudb.Operation;
import ru.vt.entities.piudb.Operation.OperationValues;
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
    default Mix getMinMix() {
        return Utils.ifNotNull(getStartingFromMix(), Version::getMix);
    }

    @JsonIgnore
    default Version getStartingFromMix() {
        for (var cv: Inner.getChartVersions(this)) {
            if (cv.getOperation().getOperationId() != OperationValues.DELETE.operationId) {
                return cv.getVersion();
            }
        }
        return null;
    }

    @JsonIgnore
    default Mix getMaxMix() {
        var versions = Inner.getChartVersions(this);
        if (versions.size() == 0) {
            return null;
        }

        var latestVersion = versions.get(versions.size() - 1);

        if (latestVersion.getOperation().getOperationId() == OperationValues.DELETE.operationId) {
            return latestVersion.getVersion().getMix().getParentMix();
        } else {
            // if last action is not DELETE then it's the latest Mix
            return MixService.latestMix;
        }
    }

    @JsonIgnore
    default List<ChartVersion> getChartVersions() {
        return Inner.getChartVersions(this);
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
    }

}
