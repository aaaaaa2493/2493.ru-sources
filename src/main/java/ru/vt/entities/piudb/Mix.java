package ru.vt.entities.piudb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
public class Mix {

    public enum MixValues {
        The1st(32, "1st"),
        The2nd(31, "2nd"),
        The3rd(30, "3rd"),
        SeasonEvolution(29, "Season Evolution"),
        Collection(28, "Collection"),
        PerfectCollection(27, "Perfect Collection"),
        Extra(26, "Extra"),
        Premiere(24, "Premiere"),
        Prex(23, "Prex"),
        Rebirth(22, "Rebirth"),
        Premiere2(21, "Premiere 2"),
        Prex2(20, "Prex 2"),
        Premiere3(19, "Premiere 3"),
        Prex3(18, "Prex 3"),
        Exceed(17, "Exceed"),
        Exceed2(16, "Exceed 2"),
        Zero(15, "Zero"),
        NX(14, "NX"),
        NX2(13, "NX2"),
        NXAbsolute(12, "NX Absolute"),
        Fiesta(11, "Fiesta"),
        FiestaEx(9,"Fiesta EX"),
        Fiesta2(7, "Fiesta 2"),
        Prime(1, "Prime"),
        Prime2(33, "Prime 2"),
        XX(34, "XX"),
        Phoenix(35, "Phoenix"),

        ;

        public final int mixId;
        public final String str;

        MixValues(int mixId, String str) {
            this.mixId = mixId;
            this.str = str;
        }

        public static MixValues of(int mixId) {
            return intToMix.get(mixId);
        }

        public static MixValues of(String mix) {
            return strToMix.get(mix);
        }

        public static final Map<String, MixValues> strToMix;
        public static final Map<Integer, MixValues> intToMix;

        static {
            var strMix = new HashMap<String, MixValues>();
            var intMix = new HashMap<Integer, MixValues>();
            for (var mix : MixValues.values()) {
                strMix.put(mix.str, mix);
                intMix.put(mix.mixId, mix);
            }
            strToMix = Collections.unmodifiableMap(strMix);
            intToMix = Collections.unmodifiableMap(intMix);
        }

        @Override
        public String toString() {
            return str;
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

    public MixValues enumValue() {
        return MixValues.of(mixId);
    }

    public String toString() {
        var mix = enumValue();
        if (mix != null) {
            return mix.toString();
        } else {
            return "???";
        }
    }

}
