package ru.vt.entities.piudb;

import lombok.Data;
import ru.vt.entities.pumpking.PumpkingDuration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Cut {

    public enum CutValues {
        ShortCut(1),
        Arcade(2),
        Remix(3),
        FullSong(4);

        public final int cutId;

        CutValues(int cutId) {
            this.cutId = cutId;
        }

        public static CutValues fromPumpking(PumpkingDuration duration) {
            return switch (duration) {
                case Standard -> Arcade;
                case Full -> FullSong;
                case Remix -> Remix;
                case Short -> ShortCut;
            };
        }
    }

    @Id
    int cutId;

    @Column(name = "internalTitle")
    String name;

    int sortOrder;

    @Override
    public String toString() {
        return name;
    }

}
