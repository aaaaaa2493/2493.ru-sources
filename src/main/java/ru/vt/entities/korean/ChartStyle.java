package ru.vt.entities.korean;

import ru.vt.utils.Utils;

public enum ChartStyle {
    DRILL,
    GIMMICK,
    TWIST,
    BRACKET,
    SIDEHALF;

    public String str(boolean isDouble) {
        String s;
        if (this == SIDEHALF) {
            if (isDouble) {
                s = "HALF";
            } else {
                s = "SIDE";
            }
        } else {
            s = name();
        }
        return Utils.capitalize(s);
    }

    @Override
    public String toString() {
        return Utils.capitalize(name());
    }
}
