package ru.vt.entities.piudb;

import lombok.Data;

@Data
public class ChartVersion implements Comparable<ChartVersion> {
    Version version;
    Operation operation;

    public ChartVersion(Version v, Operation o) {
        version = v;
        operation = o;
    }

    public String toString() {
        return operation.name + " " + version;
    }

    @Override
    public int compareTo(ChartVersion o) {
        return version.sortOrder - o.version.sortOrder;
    }
}
