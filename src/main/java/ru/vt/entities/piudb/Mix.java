package ru.vt.entities.piudb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.NoSuchElementException;

@Data
@Entity
public class Mix {

    public enum MixValues {
        The1st(32),
        The2nd(31),
        The3rd(30),
        TheOBG(29),
        Collection(28),
        PerfectCollection(27),
        Extra(26),
        Premiere(24),
        Prex(23),
        Rebirth(22),
        Premiere2(21),
        Prex2(20),
        Premiere3(19),
        Prex3(18),
        Exceed(17),
        Exceed2(16),
        Zero(15),
        NX(14),
        NX2(13),
        NXAbsolute(12),
        Fiesta(11),
        FiestaEx(9),
        Fiesta2(7),
        Prime(1),
        Prime2(33),
        XX(34);

        public final int mixId;

        MixValues(int mixId) {
            this.mixId = mixId;
        }

        static MixValues of(int mixId) {
            for (var mix : MixValues.values()) {
                if (mix.mixId == mixId) {
                    return mix;
                }
            }
            throw new NoSuchElementException("For mixId = " + mixId);
        }

        @Override
        public String toString() {
            return switch (this) {
                case The1st -> "1st";
                case The2nd -> "2nd";
                case The3rd -> "3rd";
                case TheOBG -> "The O.B.G.";
                case Collection -> "Collection";
                case PerfectCollection -> "Perfect Collection";
                case Extra -> "Extra";
                case Premiere -> "Premiere";
                case Prex -> "Prex";
                case Rebirth -> "Rebirth";
                case Premiere2 -> "Premiere 2";
                case Prex2 -> "Prex 2";
                case Premiere3 -> "Premiere 3";
                case Prex3 -> "Prex 3";
                case Exceed -> "Exceed";
                case Exceed2 -> "Exceed 2";
                case Zero -> "Zero";
                case NX -> "NX";
                case NX2 -> "NX2";
                case NXAbsolute -> "NXA";
                case Fiesta -> "Fiesta";
                case FiestaEx -> "Fiesta Ex";
                case Fiesta2 -> "Fiesta 2";
                case Prime -> "Prime";
                case Prime2 -> "Prime2";
                case XX -> "XX";
            };
        }
    }

    @Id
    @JsonIgnore
    int mixId;

    @ManyToOne
    @JsonIgnore
    Game game;

    @Column(name = "internalTitle")
    String name;

    @OneToOne
    @JoinColumn(name = "parentMixId")
    @JsonIgnore
    Mix parentMix;

    int sortOrder;

    public String toString() {
        return MixValues.of(mixId).toString();
    }

}
