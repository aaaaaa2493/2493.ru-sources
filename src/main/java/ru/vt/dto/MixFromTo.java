package ru.vt.dto;

import lombok.Data;
import ru.vt.entities.piudb.Mix;

@Data
public class MixFromTo {
    Mix from;
    Mix to;

    public MixFromTo(Mix from, Mix to) {
        this.from = from;
        this.to = to;
    }

    public boolean between(Mix mix) {
        return from.getSortOrder() <= mix.getSortOrder()
            && to.getSortOrder() >= mix.getSortOrder();
    }

    public boolean intersects(MixFromTo mixFromTo) {
        return between(mixFromTo.getFrom()) || between(mixFromTo.getTo());
    }

    public MixFromTo intersection(MixFromTo mixFromTo) {
        if (!intersects(mixFromTo)) {
            return null;
        }

        Mix min;
        Mix max;

        if (from.getSortOrder() <= mixFromTo.from.getSortOrder()) {
            min = mixFromTo.from;
        } else {
            min = from;
        }

        if (to.getSortOrder() >= mixFromTo.to.getSortOrder()) {
            max = mixFromTo.to;
        } else {
            max = to;
        }

        return new MixFromTo(min, max);
    }

    @Override
    public String toString() {
        if (from.getSortOrder() == to.getSortOrder()) {
            return String.valueOf(from);
        }
        return from + " - " + to;
    }
}
