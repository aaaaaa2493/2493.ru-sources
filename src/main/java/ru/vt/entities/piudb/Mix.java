package ru.vt.entities.piudb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
        Fiesta2(8),
        Prime(1),
        Prime2(33),
        XX(34);

        public final int mixId;

        MixValues(int mixId) {
            this.mixId = mixId;
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
        return switch (mixId) {
            case 12 -> "NXA";
            case 13 -> "NX2";
            case 14 -> "NX";
            case 29 -> "The O.B.G.";
            case 30 -> "3rd";
            case 31 -> "2nd";
            case 32 -> "1st";
            default -> name;
        };
    }

}
