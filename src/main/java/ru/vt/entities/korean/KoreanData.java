package ru.vt.entities.korean;

import lombok.Data;

import java.util.List;

import static ru.vt.entities.korean.ChartStyle.BRACKET;
import static ru.vt.entities.korean.ChartStyle.DRILL;
import static ru.vt.entities.korean.ChartStyle.GIMMICK;
import static ru.vt.entities.korean.ChartStyle.SIDEHALF;
import static ru.vt.entities.korean.ChartStyle.TWIST;

@Data
public class KoreanData {
    private static KoreanSongData s(String id, String name, KoreanChartData... charts) {
        return new KoreanSongData(id, name, List.of(charts));
    }

    private static KoreanChartData c(String diff, ChartStyle... style) {
        return new KoreanChartData(diff, List.of(style));
    }

    public static List<KoreanSongData> data = List.of(
        s("504", "All I Want For X-mas"
        ),
        s("503", "Rolling Christmas"
            ,c("D22", TWIST)
        ),
        s("505", "Beethoven Virus"
        ),
        s("404", "Solitary"
            ,c("S17", TWIST)
            ,c("S18", GIMMICK)
        ),
        s("1415", "Hestia"
            ,c("S19", DRILL)
        ),
        s("405", "Mr. Larpus"
            ,c("D16", TWIST)
            ,c("D17", DRILL, TWIST)
        ),
        s("301", "Final Audition 2"
            ,c("S17", TWIST)
            ,c("D18", TWIST)
            ,c("D20", TWIST)
        ),
        s("302", "Naissance"
            ,c("S16", GIMMICK)
        ),
        s("303", "Turkey March"
            ,c("S18", TWIST)
        ),
        s("309", "Midnight Blue"
        ),
        s("204", "Final Audition"
            ,c("S17", TWIST, BRACKET)
            ,c("D18", TWIST)
        ),
        s("205", "Extravaganza"
            ,c("S17", GIMMICK, DRILL)
            ,c("S21", TWIST)
            ,c("D18", DRILL)
            ,c("D20", DRILL)
        ),
        s("224", "Repeatorment Remix REMIX"
            ,c("S18", DRILL)
            ,c("D22", DRILL)
        ),
        s("101", "Ignition Starts"
        ),
        s("102", "Hypnosis"
        ),
        s("516", "Slam"
            ,c("D23", BRACKET)
        ),
        s("414", "Run to you"
        ),
        s("312", "Don't Bother Me"
        ),
        s("318", "We Are"
        ),
        s("212", "Com'Back"
        ),
        s("213", "Mobius Strip"
        ),
        s("109", "Funky Tonight"
        ),
        s("112", "Another Truth"
            ,c("S16", BRACKET)
            ,c("D17", BRACKET)
            ,c("D18", BRACKET)
            ,c("D21", BRACKET)
        ),
        s("802", "Bee"
            ,c("S16", DRILL, TWIST)
        ),
        s("820", "Beat of The War"
            ,c("S18", GIMMICK)
            ,c("S21", GIMMICK)
            ,c("D23", GIMMICK)
        ),
        s("826", "Come to Me"
            ,c("S17", GIMMICK)
        ),
        s("701", "Dr. M"
            ,c("S16", DRILL)
        ),
        s("702", "Emperor"
            ,c("S16", BRACKET)
            ,c("D17", BRACKET)
        ),
        s("704", "Love is a Danger Zone"
            ,c("S17", TWIST)
            ,c("S19", TWIST)
            ,c("D21", TWIST)
        ),
        s("705", "Maria"
        ),
        s("707", "My Way"
            ,c("D15", TWIST,  BRACKET)
            ,c("D18", TWIST)
        ),
        s("708", "Point Break"
        ),
        s("711", "Winter"
            ,c("S16", DRILL)
        ),
        s("712", "Will-O-The-Wisp"
        ),
        s("713", "Till the end of time"
            ,c("D16", SIDEHALF)
        ),
        s("714", "Oy Oy Oy"
        ),
        s("717", "Set me up"
            ,c("D16", SIDEHALF)
        ),
        s("718", "Dance with me"
            ,c("D18", GIMMICK)
        ),
        s("735", "Vook"
            ,c("S15", DRILL)
            ,c("S20", TWIST)
        ),
        s("736", "Csikos Post"
            ,c("D16", DRILL, TWIST)
            ,c("D22", DRILL)
        ),
        s("911", "Chicken Wing"
            ,c("S21", TWIST)
            ,c("D22", DRILL, BRACKET)
        ),
        s("922", "Final Audition Ep. 1"
            ,c("S20", TWIST)
            ,c("D23", BRACKET)
        ),
        s("E928", "EXTRA BanYa Remix REMIX"
        ),
        s("906", "Starian"
        ),
        s("909", "Mistake"
        ),
        s("C01", "Beat of The War 2"
            ,c("S16", DRILL, BRACKET)
            ,c("D20", GIMMICK, BRACKET)
        ),
        s("C02", "Moonlight"
            ,c("S18", DRILL, GIMMICK)
            ,c("S19", GIMMICK)
            ,c("D20", DRILL, GIMMICK)
            ,c("D21", DRILL, GIMMICK)
        ),
        s("C03", "Witch Doctor"
            ,c("S17", DRILL)
            ,c("S19", DRILL)
            ,c("S21", BRACKET)
            ,c("D19", DRILL)
            ,c("D22", BRACKET, SIDEHALF)
            ,c("D23", DRILL)
        ),
        s("C04", "Love is a Danger Zone pt. 2"
            ,c("S15", TWIST)
            ,c("S22", TWIST)
            ,c("D23", TWIST)
        ),
        s("C05", "Phantom"
            ,c("S18", BRACKET)
            ,c("S19", DRILL, SIDEHALF)
            ,c("D19", GIMMICK, SIDEHALF)
        ),
        s("C06", "Papa Gonzales"
            ,c("D18", DRILL, TWIST)
            ,c("D23", GIMMICK)
        ),
        s("C41", "Love is a danger zone (try to B.P.M.) REMIX"
            ,c("S15", TWIST, BRACKET)
            ,c("S21", TWIST)
            ,c("D16", TWIST)
            ,c("D23", SIDEHALF)
        ),
        s("DC04", "Love is a Danger Zone pt.2 another"
            ,c("S16", DRILL, SIDEHALF)
        ),
        s("B16", "JBong"
            ,c("S15", GIMMICK)
        ),
        s("B17", "Hi Bi"
            ,c("S19", TWIST)
            ,c("D20", GIMMICK, BRACKET)
        ),
        s("B18", "Solitary 2"
            ,c("S17", TWIST)
            ,c("D19", GIMMICK, TWIST)
            ,c("D21", BRACKET)
        ),
        s("B19", "Canon D"
            ,c("S20", TWIST)
            ,c("S21", TWIST)
        ),
        s("B28", "Tream Vook of the war REMIX REMIX"
            ,c("S19", DRILL)
            ,c("D15", DRILL, TWIST)
        ),
        s("B29", "Banya Classic Remix REMIX"
        ),
        s("B56", "BANYA HIPHOP REMIX REMIX"
            ,c("D20", BRACKET)
        ),
        s("B57", "Canon D FULL Song MIX FULL SONG"
        ),
        s("A01", "Final Audition 3"
            ,c("S16", TWIST)
            ,c("D19", TWIST)
        ),
        s("A02", "Naissance 2"
            ,c("D15", TWIST)
        ),
        s("A03", "Monkey Fingers"
        ),
        s("A04", "Blazing"
            ,c("S19", TWIST)
        ),
        s("A05", "Pump me Amadeus"
        ),
        s("A06", "X Treme"
        ),
        s("A07", "Get Up!"
            ,c("S21", GIMMICK)
            ,c("D23", GIMMICK)
        ),
        s("C14", "Chung Hwa Ban Jeom"
        ),
        s("B02", "HOT"
        ),
        s("B06", "Deja Vu"
        ),
        s("B08", "Lion's roar"
        ),
        s("B13", "I'll Give You All My Love"
        ),
        s("B51", "DIGNITY FULL SONG MIX FULL SONG"
        ),
        s("A08", "Dignity"
            ,c("S22", DRILL, TWIST)
            ,c("D17", TWIST)
        ),
        s("A11", "What Do You Really Want?"
        ),
        s("F01", "Blaze Emotion"
        ),
        s("F02", "Cannon X.1"
            ,c("S21", SIDEHALF)
        ),
        s("F03", "Chopsticks Challenge"
        ),
        s("F22", "The People didn't know"
            ,c("S15", TWIST, SIDEHALF)
            ,c("D17", SIDEHALF)
        ),
        s("F23", "DJ Otada"
            ,c("D20", SIDEHALF)
        ),
        s("F24", "K.O.A : Alice In Wonderworld"
            ,c("D18", GIMMICK)
        ),
        s("F25", "My Dreams"
            ,c("S18", TWIST)
            ,c("D18", TWIST)
        ),
        s("F26", "Toccata"
            ,c("S17", TWIST)
            ,c("D17", TWIST)
        ),
        s("F29", "Final Audition Ep. 2-X"
            ,c("S17", GIMMICK)
            ,c("D21", GIMMICK)
        ),
        s("F31", "The People didn't know Pumping up REMIX"
        ),
        s("F33", "Caprice of DJ Otada REMIX"
            ,c("S20", GIMMICK)
        ),
        s("F34", "Dr. KOA REMIX"
            ,c("S18", TWIST, SIDEHALF)
        ),
        s("F50", "Chopsticks Challenge FULL SONG"
            ,c("S15", TWIST)
            ,c("D19", GIMMICK, TWIST)
        ),
        s("FE13B", "Pumptris 8Bit ver."
            ,c("S16", DRILL, SIDEHALF)
            ,c("S17", DRILL, SIDEHALF)
        ),
        s("E01", "Solitary 1.5"
            ,c("S16", DRILL)
        ),
        s("E02", "Beat The Ghost"
            ,c("S18", GIMMICK, BRACKET)
            ,c("S20", GIMMICK)
            ,c("D20", GIMMICK)
        ),
        s("E03", "Caprice of Otada"
            ,c("D21", GIMMICK)
        ),
        s("E05", "Monkey Fingers 2"
            ,c("S17", BRACKET)
            ,c("D19", GIMMICK)
        ),
        s("E12", "Faster Z"
            ,c("S19", TWIST)
            ,c("S20", DRILL)
            ,c("D21", TWIST)
            ,c("D23", TWIST)
        ),
        s("E13", "Pumptris Quattro"
            ,c("S18", BRACKET)
            ,c("S19", TWIST)
            ,c("D15", GIMMICK, BRACKET)
            ,c("D20", SIDEHALF)
            ,c("D22", TWIST)
        ),
        s("E24", "Higgledy Piggledy"
            ,c("S16", GIMMICK)
            ,c("S19", TWIST)
            ,c("D15", SIDEHALF)
        ),
        s("E50", "Banya-P Guitar  Remix REMIX"
            ,c("S22", GIMMICK)
        ),
        s("D01", "Witch Doctor #1"
            ,c("S16", GIMMICK, TWIST)
            ,c("S17", GIMMICK, TWIST)
            ,c("S19", GIMMICK, TWIST)
            ,c("D17", SIDEHALF)
            ,c("D20", GIMMICK, TWIST)
            ,c("D21", GIMMICK, SIDEHALF)
        ),
        s("D02", "Arch of Darkness"
            ,c("S17", GIMMICK)
            ,c("D20", GIMMICK, BRACKET)
        ),
        s("D03", "Chimera"
            ,c("S19", GIMMICK)
        ),
        s("D15", "Do U Know That-Old School"
        ),
        s("D16", "Gun Rock"
            ,c("S17", DRILL)
            ,c("S20", BRACKET)
        ),
        s("D17", "Bullfighter's Song"
            ,c("D21", GIMMICK)
        ),
        s("D18", "Ugly Dee"
            ,c("S17", GIMMICK)
            ,c("D15", GIMMICK)
            ,c("D18", GIMMICK, BRACKET)
        ),
        s("D28", "Final Audition Ep. 2-1"
            ,c("S18", GIMMICK, SIDEHALF)
            ,c("S21", GIMMICK, SIDEHALF)
            ,c("S22", GIMMICK, TWIST)
            ,c("D22", TWIST)
            ,c("D23", GIMMICK, TWIST)
        ),
        s("D30", "Final Audition Ep. 2-2"
            ,c("S17", DRILL, TWIST)
            ,c("S18", TWIST, DRILL)
            ,c("S21", TWIST, SIDEHALF)
            ,c("S22", GIMMICK)
            ,c("D19", DRILL, GIMMICK)
            ,c("D23", GIMMICK)
        ),
        s("D34", "WI-EX-DOC-VA REMIX"
            ,c("S15", BRACKET)
            ,c("S21", DRILL)
            ,c("D16", TWIST, BRACKET)
        ),
        s("D35", "BEMERA REMIX"
        ),
        s("D41", "Love is a Danger Zone 2 FULL SONG"
            ,c("S19", GIMMICK, TWIST)
            ,c("D21", DRILL, GIMMICK)
        ),
        s("D42", "Beat of the War 2 FULL SONG"
            ,c("S20", GIMMICK, BRACKET)
            ,c("D21", GIMMICK, BRACKET)
        ),
        s("F28", "Dawn of the Apocalypse"
        ),
        s("F65", "Beat # No.4 FULL SONG"
        ),
        s("E20", "Beat # No.4"
        ),
        s("D32", "Groove Party REMIX"
        ),
        s("F13", "Panuelito Rojo"
        ),
        s("F14", "Procedimientos Para Llegar A Un Comun Acuerdo"
        ),
        s("F15", "Digan Lo Que Digan"
        ),
        s("F37", "Nina PXNDX Mix REMIX"
        ),
        s("F38", "Big metra Remix REMIX"
        ),
        s("F53", "Panuelito Rojo FULL SONG"
        ),
        s("F54", "Procedimientos Para Llegar A Un Comun Acuerdo FULL SONG"
        ),
        s("F59", "Digan Lo Que Digan FULL SONG"
        ),
        s("F60", "Trato De No Trabarme FULL SONG"
        ),
        s("E27", "Dance All Night"
        ),
        s("E28", "Dance Vibrations"
        ),
        s("E29", "Energizer"
        ),
        s("1001", "XTREE"
            ,c("D15", TWIST, BRACKET)
        ),
        s("1002", "Sorceress Elise"
            ,c("S21", DRILL)
        ),
        s("1008", "U Got 2 Know"
            ,c("S17", GIMMICK, TWIST)
            ,c("S19", TWIST)
            ,c("S20", GIMMICK, TWIST)
            ,c("D18", TWIST)
        ),
        s("1013", "Destination"
            ,c("S17", GIMMICK)
            ,c("S19", GIMMICK, TWIST)
            ,c("S20", GIMMICK)
            ,c("D20", GIMMICK)
        ),
        s("1017", "Vacuum"
            ,c("S19", DRILL)
            ,c("S21", DRILL, BRACKET)
            ,c("D21", DRILL)
        ),
        s("1022", "Xenesis"
            ,c("D15", BRACKET, SIDEHALF)
        ),
        s("1023", "Arirang"
            ,c("S19", TWIST)
            ,c("S22", TWIST, BRACKET)
        ),
        s("1024", "Tek -Club Copenhagen-"
            ,c("S16", GIMMICK, TWIST)
            ,c("D17", GIMMICK)
        ),
        s("1025", "Hello William"
            ,c("S17", DRILL)
            ,c("S19", SIDEHALF)
            ,c("D20", SIDEHALF)
        ),
        s("1026", "Turkey March -Minimal Tunes-"
            ,c("S17", SIDEHALF)
            ,c("D18", TWIST)
        ),
        s("1027", "Get Up (and go)"
        ),
        s("1028", "Phantom -Intermezzo-"
            ,c("S15", TWIST, SIDEHALF)
            ,c("S19", GIMMICK, TWIST)
            ,c("D17", SIDEHALF)
            ,c("D19", SIDEHALF)
            ,c("D21", TWIST)
        ),
        s("1031", "B.P Classic Remix REMIX"
            ,c("S18", DRILL)
            ,c("D19", GIMMICK)
        ),
        s("1033", "PaPa helloizing REMIX"
        ),
        s("1034", "B.P Classic Remix 2 REMIX"
        ),
        s("1035", "Hard Core Rock Mix REMIX"
        ),
        s("1037", "Set Up Me2 Mix REMIX"
            ,c("S17", GIMMICK)
        ),
        s("1039", "msgoon RMX pt.6 REMIX"
            ,c("S21", TWIST)
            ,c("D20", TWIST, SIDEHALF)
        ),
        s("1048", "Final Audition 2 SHORT CUT"
            ,c("S17", GIMMICK, BRACKET)
            ,c("D18", TWIST)
        ),
        s("1049", "Final Audition 3 SHORT CUT"
        ),
        s("1050", "Final Audition EP. 2-X SHORT CUT"
            ,c("S20", BRACKET)
            ,c("D20", GIMMICK, BRACKET)
        ),
        s("1051", "Love is a Danger Zone SHORT CUT"
            ,c("S20", TWIST)
            ,c("D17", SIDEHALF)
        ),
        s("1052", "Love is a Danger Zone pt. 2 SHORT CUT"
            ,c("S19", TWIST)
            ,c("D22", TWIST)
        ),
        s("1053", "Extravaganza SHORT CUT"
            ,c("S15", TWIST)
            ,c("D15", TWIST, BRACKET)
        ),
        s("1054", "CHICKEN WING SHORT CUT"
            ,c("S20", DRILL, BRACKET)
        ),
        s("1055", "Winter SHORT CUT"
        ),
        s("1056", "Solitary 2 SHORT CUT"
            ,c("D17", GIMMICK, SIDEHALF)
        ),
        s("1057", "Moonlight SHORT CUT"
        ),
        s("1058", "Witch Doctor SHORT CUT"
            ,c("S20", DRILL, GIMMICK)
            ,c("D17", DRILL, GIMMICK)
        ),
        s("1059", "Exceed2 Opening SHORT CUT"
            ,c("D18", BRACKET)
        ),
        s("1060", "NX Opening SHORT CUT"
            ,c("S16", DRILL, TWIST)
        ),
        s("1061", "K.O.A : Alice in Wonderworld SHORT CUT"
            ,c("S16", GIMMICK, BRACKET)
            ,c("D18", GIMMICK)
        ),
        s("1062", "Bemera SHORT CUT"
            ,c("S21", DRILL)
        ),
        s("1063", "Pumptris 8Bit ver. SHORT CUT"
        ),
        s("1064", "Destination SHORT CUT"
        ),
        s("1094", "Tepris"
            ,c("D19", GIMMICK)
        ),
        s("1095", "Napalm"
            ,c("D15", TWIST)
            ,c("D22", BRACKET)
        ),
        s("1042", "Deja Vu FULL SONG"
        ),
        s("1044", "Dawn of the Apocalypse FULL SONG"
        ),
        s("1018", "NARCISISTA POR EXCELENCIA"
        ),
        s("1043", "NARCISISTA POR EXCELENCIA FULL SONG"
        ),
        s("1065", "Procedimientos Para Llegar A Un Comun Acuerdo SHORT CUT"
        ),
        s("1096", "Dieciseis"
        ),
        s("1101", "Cleaner"
            ,c("S21", BRACKET)
            ,c("D20", DRILL)
        ),
        s("1102", "Interference"
            ,c("D22", GIMMICK, TWIST)
        ),
        s("1103", "Reality"
            ,c("S15", TWIST, SIDEHALF)
            ,c("S17", TWIST)
            ,c("D15", GIMMICK, SIDEHALF)
        ),
        s("1104", "Take Out"
            ,c("S17", GIMMICK)
            ,c("S20", GIMMICK)
            ,c("D19", DRILL, GIMMICK)
            ,c("D22", GIMMICK)
            ,c("D23", GIMMICK, TWIST)
        ),
        s("1105", "Butterfly"
            ,c("S16", TWIST)
            ,c("D17", SIDEHALF)
        ),
        s("1106", "Overblow"
            ,c("S20", DRILL)
        ),
        s("1107", "We Got 2 Know"
            ,c("S20", GIMMICK)
            ,c("D18", TWIST)
        ),
        s("1123", "Hungarian Dance V"
            ,c("S18", DRILL, BRACKET)
            ,c("D21", TWIST)
        ),
        s("1124", "The Devil"
            ,c("S19", GIMMICK)
        ),
        s("1126", "Native"
        ),
        s("1131", "Vacuum Cleaner REMIX"
            ,c("S15", BRACKET)
        ),
        s("1132", "Everybody Got 2 Know REMIX"
            ,c("S19", GIMMICK)
            ,c("D18", GIMMICK, TWIST)
        ),
        s("1135", "Interference FULL SONG"
            ,c("S18", GIMMICK)
            ,c("D15", GIMMICK)
            ,c("D18", GIMMICK)
        ),
        s("1145", "Trotpris SHORT CUT"
        ),
        s("1146", "Cleaner SHORT CUT"
            ,c("S21", BRACKET)
        ),
        s("1147", "Take Out SHORT CUT"
        ),
        s("1148", "Overblow SHORT CUT"
        ),
        s("1152", "Pavane"
            ,c("D22", DRILL)
        ),
        s("1153", "Pine Nut"
            ,c("S20", GIMMICK)
            ,c("D16", DRILL, TWIST)
            ,c("D22", GIMMICK)
        ),
        s("1154", "ASDF"
            ,c("D17", DRILL)
        ),
        s("1160", "Jonathan's Dream"
            ,c("S16", DRILL, TWIST)
            ,c("S18", TWIST)
            ,c("D15", TWIST)
            ,c("D19", TWIST, SIDEHALF)
        ),
        s("1114", "Superman"
        ),
        s("1117", "No.3"
        ),
        s("1118", "Like A Man"
        ),
        s("1119", "Crashday"
        ),
        s("1140", "Like A Man FULL SONG"
        ),
        s("1141", "No.3 FULL SONG"
        ),
        s("1142", "Crashday FULL SONG"
        ),
        s("1120", "What Happened"
            ,c("S21", BRACKET)
            ,c("D20", BRACKET)
            ,c("D23", BRACKET)
        ),
        s("1121", "Gargoyle"
            ,c("S18", TWIST, GIMMICK)
            ,c("S22", DRILL, TWIST)
            ,c("D23", DRILL, TWIST)
        ),
        s("1122", "Allegro Con Fuoco"
        ),
        s("1125", "X-Rave"
        ),
        s("1127", "Smells Like A Chocolate"
            ,c("D17", GIMMICK)
        ),
        s("1128", "Necromancy"
            ,c("S18", GIMMICK, BRACKET)
            ,c("S20", TWIST, BRACKET)
            ,c("D23", GIMMICK, TWIST)
        ),
        s("1149", "X-Rave SHORT CUT"
        ),
        s("1159", "Rave Until The Night Is Over"
        ),
        s("1228", "Dream To Nightmare"
            ,c("S19", DRILL)
            ,c("S21", GIMMICK, BRACKET)
            ,c("D22", GIMMICK)
            ,c("D23", GIMMICK, BRACKET)
        ),
        s("1240", "VVV"
            ,c("S18", GIMMICK)
            ,c("D17", GIMMICK)
            ,c("D19", GIMMICK)
        ),
        s("1301", "Pop The Track"
            ,c("S16", DRILL)
            ,c("S18", DRILL)
            ,c("D20", GIMMICK)
        ),
        s("1302", "Electric"
        ),
        s("1303", "Passacaglia"
            ,c("S17", GIMMICK)
            ,c("S19", GIMMICK)
            ,c("D18", GIMMICK)
            ,c("D22", BRACKET)
            ,c("D23", GIMMICK)
        ),
        s("1304", "Baroque Virus"
            ,c("S17", GIMMICK)
        ),
        s("1305", "Elise"
            ,c("S21", GIMMICK, BRACKET)
            ,c("D23", GIMMICK, BRACKET)
        ),
        s("1306", "Ignis Fatuus(DM Ashura Mix)"
            ,c("S15", DRILL, GIMMICK)
            ,c("S19", DRILL, GIMMICK)
            ,c("S22", GIMMICK)
            ,c("D17", DRILL, GIMMICK)
            ,c("D18", DRILL, GIMMICK)
        ),
        s("1307", "Love Is A Danger Zone (Cranky Mix)"
            ,c("S20", TWIST)
            ,c("S21", TWIST)
            ,c("D16", SIDEHALF)
            ,c("D20", TWIST, SIDEHALF)
            ,c("D23", TWIST)
        ),
        s("1308", "Hypnosis(SynthWulf Mix)"
            ,c("S17", DRILL, GIMMICK)
            ,c("S20", GIMMICK)
            ,c("D15", SIDEHALF)
            ,c("D19", GIMMICK, BRACKET)
            ,c("D22", GIMMICK, TWIST)
        ),
        s("1309", "FFF"
            ,c("D22", DRILL)
        ),
        s("1310", "Unique"
        ),
        s("1311", "Accident"
            ,c("S18", DRILL, GIMMICK)
            ,c("D20", DRILL)
        ),
        s("1312", "D"
            ,c("S18", DRILL, BRACKET)
            ,c("D20", BRACKET)
        ),
        s("1313", "U Got Me Rocking"
            ,c("S17", TWIST)
            ,c("S18", TWIST, BRACKET)
            ,c("D17", TWIST)
        ),
        s("1314", "Lucid(PIU Edit)"
            ,c("S19", GIMMICK)
            ,c("D20", GIMMICK)
            ,c("D23", DRILL, GIMMICK)
        ),
        s("1315", "Nobody"
        ),
        s("1316", "Bad Girl Good Girl"
        ),
        s("1317", "Step"
        ),
        s("1318", "I'm The Best"
        ),
        s("1319", "Can't Nobody"
        ),
        s("1320", "Shanghai Romance"
        ),
        s("1321", "Fantastic Baby"
        ),
        s("1322", "Lie"
        ),
        s("1323", "Heart Breaker"
        ),
        s("1325", "Be Mine"
        ),
        s("1326", "Crayon"
        ),
        s("1327", "Mackerel"
        ),
        s("1328", "Two Guys"
        ),
        s("1332", "One Two Three Go!"
        ),
        s("1333", "Los Malaventurados No Lloran"
        ),
        s("1334", "Sik Asik"
        ),
        s("1335", "Online"
        ),
        s("1336", "Dam"
        ),
        s("1337", "Sugar Eyes"
        ),
        s("1338", "Log In"
            ,c("S18", DRILL)
            ,c("S20", DRILL)
            ,c("D19", DRILL, BRACKET)
        ),
        s("1339", "Windmill"
            ,c("D18", SIDEHALF)
            ,c("D23", DRILL, BRACKET)
        ),
        s("1340", "Follow Me"
            ,c("D17", GIMMICK, BRACKET)
        ),
        s("1341", "Yeo rae a"
        ),
        s("1390", "Mental Rider"
            ,c("S16", GIMMICK)
            ,c("S21", TWIST)
        ),
        s("13A0", "BIG to the BANG REMIX"
        ),
        s("13A1", "Super Mackerel REMIX"
        ),
        s("13A2", "Infinity RMX REMIX"
            ,c("D19", GIMMICK)
        ),
        s("13A3", "What Are You Doin? REMIX"
            ,c("S18", DRILL)
            ,c("D23", GIMMICK)
        ),
        s("13C0", "STEP FULL SONG"
        ),
        s("13C1", "I'm The Best FULL SONG"
        ),
        s("13C2", "Shanghai Romance FULL SONG"
        ),
        s("13C3", "Fantastic Baby FULL SONG"
        ),
        s("13C4", "Can't Nobody FULL SONG"
        ),
        s("13C5", "Heart Breaker FULL SONG"
        ),
        s("13E0", "Pop The Track SHORT CUT"
            ,c("D16", GIMMICK)
        ),
        s("13E1", "Passacaglia SHORT CUT"
        ),
        s("13E2", "Ignis Fatuus(DM Ashura Mix) SHORT CUT"
            ,c("S21", DRILL)
        ),
        s("13E3", "FFF SHORT CUT"
            ,c("S18", DRILL)
        ),
        s("13E4", "Unique SHORT CUT"
        ),
        s("13E5", "U Got Me Rocking SHORT CUT"
            ,c("S17", TWIST)
        ),
        s("1201", "Monolith"
        ),
        s("1202", "Y2Z"
        ),
        s("1206", "Rockhill"
        ),
        s("1208", "Switchback"
        ),
        s("1209", "Ladybug"
        ),
        s("1212", "Hardkore of the North"
            ,c("S21", GIMMICK, TWIST)
            ,c("D15", GIMMICK)
            ,c("D22", GIMMICK)
        ),
        s("1218", "Rippin' It Up"
        ),
        s("1219", "Tribe Attacker"
            ,c("S17", DRILL, GIMMICK)
        ),
        s("1221", "Virtual Emotion"
        ),
        s("1222", "Take Me Back"
        ),
        s("1225", "RE-RAVE"
        ),
        s("1226", "Heel and Toe"
        ),
        s("1230", "Rainspark"
        ),
        s("1235", "Utopia"
            ,c("D18", DRILL)
        ),
        s("1236", "Xuxa"
            ,c("D17", DRILL, SIDEHALF)
        ),
        s("1237", "Be Alive (Raaban Inc. Mix)"
        ),
        s("1241", "Star Command"
            ,c("D15", SIDEHALF)
            ,c("D21", DRILL)
            ,c("D23", GIMMICK)
        ),
        s("1401", "Nemesis"
            ,c("S16", GIMMICK)
            ,c("S18", GIMMICK)
        ),
        s("1403", "Latino Virus"
        ),
        s("1405", "Yog-Sothoth"
        ),
        s("1407", "Chinese restaurant"
            ,c("D19", GIMMICK)
        ),
        s("1410", "Requiem"
            ,c("S16", DRILL)
            ,c("S19", GIMMICK)
            ,c("D23", GIMMICK)
        ),
        s("1413", "Meteorize"
            ,c("S15", GIMMICK, BRACKET)
            ,c("D16", BRACKET)
        ),
        s("1417", "Leakage Voltage"
            ,c("S15", GIMMICK)
            ,c("S17", GIMMICK, BRACKET)
            ,c("S21", TWIST)
            ,c("D18", GIMMICK)
            ,c("D22", TWIST, BRACKET)
        ),
        s("1419", "Super Fantasy"
            ,c("S16", GIMMICK)
            ,c("S19", GIMMICK, TWIST)
            ,c("S20", TWIST, BRACKET)
            ,c("D18", GIMMICK, SIDEHALF)
            ,c("D22", TWIST)
        ),
        s("1421", "Red Swan"
            ,c("S18", DRILL, BRACKET)
            ,c("D20", DRILL)
            ,c("DP20", GIMMICK, BRACKET)
            ,c("D22", DRILL)
        ),
        s("1426", "Allegro Piu Mosso"
            ,c("S19", TWIST, BRACKET)
        ),
        s("1429", "Rock the house"
            ,c("S16", GIMMICK)
            ,c("D18", GIMMICK)
            ,c("D22", DRILL)
        ),
        s("1436", "Robot battle"
            ,c("S18", TWIST)
            ,c("D19", DRILL)
        ),
        s("1437", "Bar Bar Bar"
        ),
        s("1439", "ELVIS"
        ),
        s("1441", "I'm Sorry"
        ),
        s("1442", "Pandora"
        ),
        s("1444", "Hate, Don't Hate!"
        ),
        s("1445", "Supermagic"
        ),
        s("1446", "Sugar Free"
        ),
        s("1447", "HER"
        ),
        s("1448", "NoNoNo"
        ),
        s("1449", "Loner"
        ),
        s("1450", "Flying duck"
        ),
        s("1451", "Ineffective Boss Without Power"
        ),
        s("1452", "One"
        ),
        s("1457", "Cosmical Rhythm"
            ,c("D15", GIMMICK, SIDEHALF)
            ,c("D18", GIMMICK, TWIST)
        ),
        s("1459", "MATADOR"
            ,c("S18", DRILL)
            ,c("S21", DRILL)
            ,c("D19", DRILL)
            ,c("D22", SIDEHALF)
        ),
        s("1462", "Ragnarok"
            ,c("S18", GIMMICK)
            ,c("S20", DRILL, TWIST)
        ),
        s("1464", "Karyawisata"
            ,c("S16", GIMMICK)
            ,c("S20", GIMMICK)
            ,c("D16", DRILL, GIMMICK)
            ,c("D22", GIMMICK, TWIST)
        ),
        s("14A0", "Beethoven Influenza REMIX"
            ,c("S21", GIMMICK, TWIST)
        ),
        s("14C0", "Bar Bar Bar FULL SONG"
        ),
        s("14C1", "Sugar Free FULL SONG"
        ),
        s("14E1", "Yog-Sothoth SHORT CUT"
            ,c("S18", DRILL)
            ,c("D16", TWIST)
            ,c("D21", DRILL, BRACKET)
        ),
        s("14E4", "Rock the house SHORT CUT"
            ,c("S18", GIMMICK)
        ),
        s("1330", "Rabiosa"
        ),
        s("1329", "Danza Kuduro"
        ),
        s("1331", "Lovumba"
        ),
        s("1467", "Limbo"
        ),
        s("1468", "Melodia"
        ),
        s("1469", "Que Viva La Vida"
        ),
        s("1438", "On and On"
        ),
        s("1463", "Achluoias"
            ,c("S15", BRACKET)
            ,c("S17", DRILL)
            ,c("S22", DRILL)
        ),
        s("14E6", "PRIME Opening SHORT CUT"
            ,c("D15", GIMMICK, BRACKET)
        ),
        s("1460", "Milky Way Galaxy"
            ,c("S18", TWIST)
            ,c("D18", GIMMICK)
        ),
        s("1454", "Selfishness"
            ,c("D18", TWIST)
            ,c("D20", GIMMICK, SIDEHALF)
        ),
        s("14E3", "Selfishness SHORT CUT"
        ),
        s("1408", "Avalanche"
            ,c("S19", DRILL)
            ,c("D19", DRILL)
            ,c("D20", DRILL)
        ),
        s("1440", "Very good"
        ),
        s("14A1", "Avalanquiem REMIX"
        ),
        s("1411", "You Got Me Crazy"
            ,c("S17", TWIST)
            ,c("S18", TWIST)
        ),
        s("1443", "Venus"
        ),
        s("1425", "Sugar Conspiracy Theory"
            ,c("S19", DRILL, TWIST)
        ),
        s("1428", "Move That Body!"
        ),
        s("14E5", "Move That Body! SHORT CUT"
            ,c("D18", TWIST)
        ),
        s("1481", "Bad Apple!! feat. Nomico"
            ,c("S15", GIMMICK)
            ,c("S19", TWIST)
            ,c("D18", TWIST, SIDEHALF)
            ,c("D20", TWIST)
        ),
        s("1480", "Smile Diary"
            ,c("S19", SIDEHALF)
        ),
        s("1479", "Mitotsudaira"
            ,c("S18", TWIST)
        ),
        s("1478", "Sudden Romance [PIU Edit]"
            ,c("S19", BRACKET)
            ,c("D19", BRACKET)
        ),
        s("1477", "Imprinting"
            ,c("S17", DRILL)
            ,c("S21", TWIST)
        ),
        s("1482", "Creed - 1st Desire -"
            ,c("S21", TWIST, BRACKET)
            ,c("D23", BRACKET)
        ),
        s("1402", "Katkoi"
            ,c("S18", GIMMICK, BRACKET)
            ,c("D15", DRILL)
        ),
        s("1412", "B2"
        ),
        s("1424", "1950"
        ),
        s("1458", "Dolly Kiss"
            ,c("S17", TWIST, BRACKET)
            ,c("D15", GIMMICK)
            ,c("D21", GIMMICK, TWIST)
        ),
        s("1406", "Silhouette Effect"
            ,c("D20", DRILL, GIMMICK)
        ),
        s("1427", "Annihilator Method"
            ,c("S19", DRILL, BRACKET)
            ,c("D20", DRILL, BRACKET)
        ),
        s("14E2", "Silhouette Effect SHORT CUT"
            ,c("D17", DRILL)
        ),
        s("1483", "Sorano Shirabe"
            ,c("S18", DRILL)
            ,c("D20", DRILL)
        ),
        s("1324", "Hands Up East4A mix"
        ),
        s("1115", "Energy"
        ),
        s("1109", "Hot Issue"
        ),
        s("1143", "Hot Issue FULL SONG"
        ),
        s("1129", "K-POP Girl Group RMX REMIX"
        ),
        s("1113", "Last Farewell"
        ),
        s("1108", "Magic"
        ),
        s("1110", "Magic Girl"
        ),
        s("1136", "Magic Girl FULL SONG"
        ),
        s("1116", "Mother"
        ),
        s("1112", "Shock"
        ),
        s("1138", "Shock FULL SONG"
        ),
        s("1020", "80's Pop"
        ),
        s("1003", "Betrayer -act.2-"
            ,c("D15", DRILL)
        ),
        s("1005", "Big Beat"
        ),
        s("1007", "By chance"
        ),
        s("1021", "Do It Reggae Style"
            ,c("D15", GIMMICK)
        ),
        s("1012", "Enjoy! Enjoy!"
        ),
        s("1006", "Exciting"
        ),
        s("1015", "Fire"
        ),
        s("1045", "Fire FULL SONG"
        ),
        s("1004", "Good Life"
        ),
        s("1041", "History: We Are The ZEST REMIX"
        ),
        s("1011", "Innocent"
        ),
        s("1032", "K-POP Mix (Old & New) REMIX"
        ),
        s("1029", "Mission Possible -Blow Back-"
            ,c("S19", GIMMICK)
            ,c("D18", TWIST)
        ),
        s("1038", "msgoon RMX pt.5 REMIX"
        ),
        s("1040", "msgoon RMX pt.7 REMIX"
        ),
        s("1019", "No Rhyme No Reason"
        ),
        s("1036", "Pro POP Mix REMIX"
        ),
        s("1047", "PUMP IT UP with YOU REMIX"
        ),
        s("1030", "Pumping Jumping"
            ,c("S15", BRACKET)
            ,c("S19", GIMMICK, BRACKET)
            ,c("D17", DRILL, TWIST)
        ),
        s("1010", "The angel who lost wings"
        ),
        s("1009", "To the sky"
        ),
        s("1016", "Wanna"
        ),
        s("1046", "Wanna FULL SONG"
        ),
        s("D14", "2006. LOVE SONG"
            ,c("S15", TWIST, GIMMICK)
        ),
        s("F30", "45RPM & Eun Ji Won Mix REMIX"
        ),
        s("F05", "Adios"
        ),
        s("F17", "Change Myself"
        ),
        s("E26", "Chopstix"
        ),
        s("F12", "Crazy"
        ),
        s("F27", "Do It!"
        ),
        s("F64", "Enter the Dragon FULL SONG"
        ),
        s("D05", "Fly"
        ),
        s("E76", "FLY FULL SONG"
        ),
        s("F10", "Forward"
        ),
        s("F55", "Forward FULL SONG"
        ),
        s("E30", "Groovin' Motion"
        ),
        s("E23", "Guitar Man"
            ,c("S20", DRILL)
            ,c("D19", DRILL)
        ),
        s("D26", "Haley"
        ),
        s("D23", "Hybs"
        ),
        s("E25", "Jam O Beat"
            ,c("S15", TWIST, BRACKET)
        ),
        s("F04", "La La La"
        ),
        s("F51", "La La La FULL SONG"
        ),
        s("E04", "Money"
        ),
        s("E52", "Money Fingers REMIX"
        ),
        s("F46", "msgoon RMX pt.3 REMIX"
        ),
        s("F44", "msgoon RMX pt.1 REMIX"
        ),
        s("F40", "Novasonic Mix ver.3 REMIX"
        ),
        s("D31", "NX K-POP Dance REMIX"
        ),
        s("D06", "One Night"
        ),
        s("F16", "Pump Breakers"
        ),
        s("F06", "Slightly"
        ),
        s("F56", "Slightly FULL SONG"
        ),
        s("F43", "Turkey Virus REMIX"
        ),
        s("F11", "Uprock"
        ),
        s("E07", "Very Old Couples"
        ),
        s("D27", "We Goin' Fly Remix"
        ),
        s("C18", "Enter the Dragon"
        ),
        s("A12", "Go"
        ),
        s("C15", "Mr. Fire Fighter"
        ),
        s("C09", "My Friend"
        ),
        s("B26", "NOVARASH REMIX REMIX"
        ),
        s("C19", "Storm"
        ),
        s("807", "D Gang"
            ,c("S18", GIMMICK)
            ,c("D15", GIMMICK)
        ),
        s("821", "Empire of the Sun"
        ),
        s("703", "Get Your Groove On"
            ,c("D22", GIMMICK)
        ),
        s("811", "Hello"
        ),
        s("716", "Miss S' story"
            ,c("S19", GIMMICK)
            ,c("D19", GIMMICK)
        ),
        s("706", "Mission Possible"
        ),
        s("709", "Street show down"
            ,c("S15", TWIST)
            ,c("D18", GIMMICK, TWIST)
        ),
        s("710", "Top City"
            ,c("S20", BRACKET)
            ,c("D20", BRACKET)
        ),
        s("715", "We will meet again"
        ),
        s("306", "A nightmare"
        ),
        s("403", "Betrayer"
            ,c("D17", TWIST)
        ),
        s("307", "Close Your Eye"
        ),
        s("402", "First Love"
        ),
        s("308", "Free Style"
            ,c("D15", BRACKET)
        ),
        s("111", "Hatred"
        ),
        s("502", "N"
            ,c("D16", DRILL, BRACKET)
        ),
        s("413", "RUN!"
        ),
        s("310", "She Likes Pizza"
            ,c("S21", DRILL)
            ,c("D16", DRILL)
        ),
        s("1486", "Setsuna Trip"
        ),
        s("1487", "Trashy Innocence"
            ,c("S15", TWIST)
            ,c("D16", SIDEHALF)
        ),
        s("1484", "FOUR SEASONS OF LONELINESS verβ feat. sariyajin"
        ),
        s("1485", "Ai, Yurete..."
            ,c("S16", GIMMICK)
            ,c("D15", SIDEHALF)
            ,c("D18", SIDEHALF)
            ,c("D20", BRACKET)
        ),
        s("1453", "THE REVOLUTION"
            ,c("S19", TWIST, SIDEHALF)
            ,c("D22", TWIST)
        ),
        s("1472", "Stardust Overdrive"
            ,c("S16", GIMMICK)
            ,c("S18", DRILL, BRACKET)
            ,c("D16", GIMMICK, BRACKET)
            ,c("D19", GIMMICK, BRACKET)
            ,c("D23", BRACKET)
        ),
        s("14C5", "FOUR SEASONS OF LONELINESS verβ feat. sariyajin FULL SONG"
            ,c("D15", TWIST)
        ),
        s("14C6", "Bad Apple!! feat. Nomico FULL SONG"
            ,c("D18", BRACKET, SIDEHALF)
            ,c("D22", BRACKET, SIDEHALF)
        ),
        s("14E7", "Stardust Overdrive SHORT CUT"
            ,c("S16", BRACKET)
            ,c("D17", DRILL)
        ),
        s("1490", "PRIME"
            ,c("S18", SIDEHALF)
            ,c("S21", TWIST)
            ,c("D16", SIDEHALF)
        ),
        s("1488", "Renai Yuusha"
            ,c("S19", DRILL)
            ,c("D18", DRILL)
            ,c("D20", DRILL)
        ),
        s("1489", "Houkago Stride"
            ,c("S15", TWIST)
            ,c("D16", TWIST)
            ,c("D21", BRACKET)
        ),
        s("1475", "Amai Yuuwaku Dangerous"
        ),
        s("1422", "Hyacinth"
            ,c("S22", TWIST)
            ,c("D19", BRACKET)
        ),
        s("1473", "Reminiscence"
            ,c("DP23", GIMMICK)
        ),
        s("14E0", "Super Fantasy SHORT CUT"
            ,c("S19", TWIST)
            ,c("D17", GIMMICK)
        ),
        s("1476", "Yoropiku Pikuyoro!"
            ,c("S15", BRACKET)
        ),
        s("1491", "Bad ∞ End ∞ Night"
            ,c("S17", TWIST)
            ,c("S19", TWIST, BRACKET)
            ,c("D23", BRACKET)
        ),
        s("1465", "video out c"
            ,c("S22", BRACKET, SIDEHALF)
            ,c("D15", DRILL, SIDEHALF)
        ),
        s("14C3", "Move That Body! FULL SONG"
        ),
        s("1420", "Violet Perfume"
        ),
        s("1498", "Hypercube"
            ,c("S17", TWIST)
            ,c("D15", SIDEHALF)
            ,c("D19", SIDEHALF)
        ),
        s("1492", "Queen of the Red"
            ,c("S15", DRILL)
            ,c("D16", SIDEHALF)
            ,c("D19", DRILL)
        ),
        s("1430", "Scorpion King"
            ,c("S15", TWIST)
            ,c("S19", SIDEHALF)
            ,c("D23", GIMMICK, BRACKET)
        ),
        s("14C2", "NoNoNo FULL SONG"
        ),
        s("1493", "Idealized Romance"
            ,c("S16", GIMMICK)
            ,c("S18", TWIST, BRACKET)
        ),
        s("1431", "Point Zero One"
            ,c("S16", GIMMICK)
            ,c("S20", DRILL, TWIST)
            ,c("D17", GIMMICK)
            ,c("D22", SIDEHALF)
        ),
        s("1404", "Elysium"
            ,c("D15", BRACKET)
        ),
        s("14C7", "Creed - 1st Desire - FULL SONG"
            ,c("S22", BRACKET)
        ),
        s("1494", "Just Hold On (To All Fighters)"
            ,c("S21", TWIST)
        ),
        s("1471", "Enhance Reality"
        ),
        s("14C4", "Pandora FULL SONG"
        ),
        s("1414", "Mad5cience"
            ,c("S15", GIMMICK)
            ,c("S20", BRACKET)
            ,c("D16", GIMMICK)
        ),
        s("1433", "Red Snow"
            ,c("S19", SIDEHALF)
            ,c("D23", BRACKET)
        ),
        s("1497", "Break it Down"
            ,c("S16", GIMMICK)
            ,c("D17", GIMMICK, SIDEHALF)
            ,c("D21", GIMMICK)
        ),
        s("1496", "Heavy Rotation"
        ),
        s("1495", "Unlock"
        ),
        s("15C5", "Fallen Angel"
            ,c("S19", GIMMICK)
            ,c("D15", DRILL, TWIST)
        ),
        s("401", "Oh! Rosa"
        ),
        s("501", "Pump Jump"
            ,c("S15", TWIST, BRACKET)
            ,c("D17", BRACKET)
        ),
        s("311", "Pumping Up"
        ),
        s("1474", "Moment Day"
            ,c("S17", SIDEHALF)
        ),
        s("1409", "Force of Ra"
            ,c("S19", SIDEHALF)
        ),
        s("14F0", "Heavy Rotation SHORT CUT"
        ),
        s("1416", "Amphitryon"
            ,c("S18", GIMMICK)
            ,c("D20", GIMMICK, TWIST)
        ),
        s("1423", "Blaze emotion (Band version)"
        ),
        s("14A2", "PARADOXX REMIX"
        ),
        s("1418", "Removable Disk0"
        ),
        s("1461", "Feel My Happiness"
            ,c("D21", DRILL, BRACKET)
        ),
        s("1434", "Campanella"
            ,c("S16", BRACKET, TWIST)
        ),
        s("1470", "Across the Ocean"
        ),
        s("1499", "Like Me"
            ,c("S18", SIDEHALF)
            ,c("D19", SIDEHALF)
        ),
        s("1435", "You again my love"
        ),
        s("1557", "Acquaintance"
        ),
        s("1525", "Arcana Force"
        ),
        s("15B0", "Asterios -ReEntry-"
            ,c("D22", TWIST)
        ),
        s("1501", "Last Rebirth"
            ,c("S15", TWIST)
            ,c("D16", TWIST)
        ),
        s("1503", "Hellfire"
            ,c("S15", DRILL)
            ,c("S22", TWIST, BRACKET)
            ,c("D23", GIMMICK, BRACKET)
        ),
        s("1507", "God Mode feat. skizzo"
        ),
        s("1509", "Further"
            ,c("S15", DRILL)
            ,c("S18", DRILL)
            ,c("S22", DRILL, BRACKET)
            ,c("D17", DRILL)
            ,c("D22", DRILL)
        ),
        s("1512", "Bring Back The Beat"
            ,c("S19", BRACKET)
        ),
        s("1516", "Sarabande"
            ,c("S19", TWIST)
            ,c("D18", SIDEHALF)
        ),
        s("1544", "BANG BANG BANG"
        ),
        s("1545", "Me Gustas Tu"
        ),
        s("1546", "RHYTHM TA"
        ),
        s("1548", "PICK ME (old)"
        ),
        s("1549", "Jackpot"
        ),
        s("1551", "BOOMBAYAH"
        ),
        s("1553", "Up & Down"
        ),
        s("1555", "You're the best"
        ),
        s("1556", "NUMBER NINE"
        ),
        s("1564", "Moon Light Dance"
            ,c("D15", TWIST)
        ),
        s("15A6", "Death Moon"
            ,c("S19", TWIST)
            ,c("D21", TWIST)
        ),
        s("15A8", "Christmas Memories"
            ,c("D15", BRACKET)
        ),
        s("15B1", "Le Grand Bleu"
        ),
        s("15D0", "Vulcan REMIX"
            ,c("S22", BRACKET)
        ),
        s("15E1", "BANG BANG BANG FULL SONG"
        ),
        s("15F0", "Sarabande SHORT CUT"
            ,c("S19", TWIST)
        ),
        s("15F1", "Death Moon SHORT CUT"
            ,c("S16", DRILL)
            ,c("S19", TWIST)
            ,c("S22", DRILL)
            ,c("D18", DRILL)
        ),
        s("1210", "Kitty Cat"
            ,c("D15", SIDEHALF)
        ),
        s("F61", "Crazy FULL SONG"
        ),
        s("15E5", "Gargoyle FULL SONG"
        ),
        s("E51", "Guitar Man FULL SONG"
        ),
        s("E53", "Monkey Fingers FULL SONG"
        ),
        s("F35", "Jam O Beat # No 4 REMIX"
        ),
        s("15B7", "Clue"
            ,c("S16", GIMMICK)
            ,c("S18", GIMMICK)
            ,c("D18", GIMMICK)
            ,c("D20", GIMMICK)
            ,c("D22", DRILL, TWIST)
        ),
        s("1576", "Just Kiddin"
            ,c("S16", TWIST)
            ,c("D18", TWIST, SIDEHALF)
        ),
        s("15E0", "Me Gustas Tu FULL SONG"
        ),
        s("1547", "Chase Me"
            ,c("S20", BRACKET)
        ),
        s("1517", "Kasou Shinja"
            ,c("S20", TWIST)
            ,c("D23", TWIST)
        ),
        s("1575", "STEP"
            ,c("D18", GIMMICK)
        ),
        s("15E2", "BOOMBAYAH FULL SONG"
        ),
        s("1597", "HELIX"
            ,c("S19", GIMMICK)
        ),
        s("1598", "Hyperion"
        ),
        s("1584", "HUSH"
            ,c("D15", BRACKET)
        ),
        s("15E6", "HUSH FULL SONG"
            ,c("S15", DRILL)
        ),
        s("1554", "Good Night"
            ,c("S17", GIMMICK)
            ,c("S20", GIMMICK)
            ,c("D19", GIMMICK)
        ),
        s("1526", "Allegro Furioso"
            ,c("S17", TWIST)
            ,c("S20", BRACKET)
            ,c("D15", SIDEHALF)
            ,c("D20", BRACKET)
        ),
        s("15A7", "Super Stylin'"
        ),
        s("15B8", "Redline"
            ,c("D15", TWIST)
            ,c("D22", SIDEHALF)
        ),
        s("15F3", "PRIME2 Opening SHORT CUT"
        ),
        s("1530", "Utsushiyo No Kaze feat. Kana"
            ,c("S20", BRACKET)
            ,c("D18", GIMMICK)
            ,c("D20", TWIST, SIDEHALF)
        ),
        s("15A2", "Start On Red"
        ),
        s("15C4", "Seize My Day"
            ,c("S18", TWIST)
            ,c("D22", GIMMICK)
        ),
        s("15E3", "Up & Down FULL SONG"
        ),
        s("1552", "SOBER"
        ),
        s("1508", "Shub Niggurath"
            ,c("S15", TWIST, SIDEHALF)
            ,c("S19", GIMMICK, TWIST)
            ,c("S21", DRILL)
            ,c("D23", GIMMICK)
        ),
        s("15A4", "Magical Vacation"
            ,c("D18", SIDEHALF)
        ),
        s("15E4", "Acquaintance FULL SONG"
        ),
        s("1518", "Overblow2"
        ),
        s("1533", "Hey U"
            ,c("S15", BRACKET)
            ,c("D16", TWIST)
        ),
        s("15B5", "BEDLAM"
            ,c("S15", DRILL)
            ,c("S22", BRACKET)
        ),
        s("15D3", "Leather REMIX"
        ),
        s("1550", "Gotta Be You"
        ),
        s("15F4", "Shub Niggurath SHORT CUT"
            ,c("S21", DRILL, BRACKET)
        ),
        s("15A3", "Time Attack <Blue>"
            ,c("S15", TWIST)
            ,c("S18", TWIST)
            ,c("D16", BRACKET, SIDEHALF)
            ,c("D20", TWIST)
        ),
        s("15B9", "Kill Them!"
            ,c("S18", TWIST, SIDEHALF)
            ,c("D19", SIDEHALF)
        ),
        s("15E7", "Chase Me FULL SONG"
            ,c("S19", TWIST, BRACKET)
        ),
        s("1578", "Heart Attack"
            ,c("S16", GIMMICK)
            ,c("S18", TWIST, GIMMICK)
            ,c("D18", SIDEHALF)
            ,c("D20", GIMMICK, SIDEHALF)
        ),
        s("1577", "Nakakapagpabagabag"
            ,c("S19", GIMMICK, BRACKET)
            ,c("D16", SIDEHALF)
            ,c("D20", GIMMICK, BRACKET)
        ),
        s("1594", "Cross Time"
            ,c("S18", BRACKET)
            ,c("D22", GIMMICK)
        ),
        s("15A9", "Keep On!"
        ),
        s("1502", "Super Capriccio"
            ,c("D19", SIDEHALF)
            ,c("D23", TWIST)
        ),
        s("1529", "Anguished Unmaking"
            ,c("S17", BRACKET)
            ,c("D22", DRILL, BRACKET)
        ),
        s("1536", "Twist of Fate (feat. Ruriling)"
            ,c("S19", GIMMICK)
            ,c("D21", GIMMICK)
        ),
        s("1537", "HTTP"
            ,c("S21", TWIST, BRACKET)
            ,c("D23", BRACKET)
        ),
        s("1559", "Energetic"
            ,c("S16", GIMMICK)
            ,c("D19", GIMMICK, BRACKET)
        ),
        s("1560", "Beautiful"
        ),
        s("1562", "REALLY REALLY"
            ,c("S15", GIMMICK)
            ,c("D16", GIMMICK)
        ),
        s("1593", "V3"
            ,c("D21", GIMMICK)
        ),
        s("15B2", "INFINITY"
            ,c("S21", BRACKET)
            ,c("D23", BRACKET)
        ),
        s("15F5", "Hyperion SHORT CUT"
            ,c("S20", TWIST)
            ,c("D21", TWIST)
        ),
        s("15F6", "Kasou Shinja SHORT CUT"
            ,c("S20", DRILL, BRACKET)
            ,c("D21", SIDEHALF)
        ),
        s("1561", "PICK ME"
            ,c("S17", TWIST, SIDEHALF)
            ,c("D16", GIMMICK)
            ,c("D18", TWIST, SIDEHALF)
        ),
        s("1540", "Rave 'til the Earth's End"
        ),
        s("1595", "The Festival of Ghost 2 (Sneak)"
            ,c("S16", GIMMICK)
            ,c("S18", TWIST)
            ,c("S20", BRACKET)
            ,c("D17", GIMMICK, TWIST)
            ,c("D20", BRACKET, SIDEHALF)
        ),
        s("15C0", "Donatello"
            ,c("S17", DRILL)
            ,c("S21", TWIST)
        ),
        s("1538", "Up & Up (Produced by AWAL)"
        ),
        s("1539", "Travel to Future"
            ,c("D23", BRACKET)
        ),
        s("1504", "Tritium"
        ),
        s("1511", "Silver Beat feat. ChisaUezono"
        ),
        s("15B3", "Gothique Resonance"
            ,c("S20", SIDEHALF)
            ,c("D21", SIDEHALF)
        ),
        s("1541", "Awakening"
            ,c("S16", GIMMICK)
            ,c("S19", GIMMICK, TWIST)
            ,c("D17", GIMMICK)
            ,c("D20", GIMMICK, TWIST)
        ),
        s("1524", "Passing Rider"
            ,c("S19", GIMMICK)
            ,c("D20", GIMMICK)
        ),
        s("1505", "Cross Over"
            ,c("S18", GIMMICK)
            ,c("S22", GIMMICK)
            ,c("D23", GIMMICK)
        ),
        s("1543", "Waltz of Doge"
            ,c("S19", GIMMICK, BRACKET)
            ,c("D20", BRACKET)
        ),
        s("15A0", "Black Dragon"
            ,c("S20", TWIST)
        ),
        s("15B6", "A Site De La Rue"
            ,c("S16", GIMMICK)
            ,c("S19", GIMMICK)
        ),
        s("1513", "Break Out"
            ,c("D20", BRACKET)
        ),
        s("1510", "The Quick Brown Fox Jumps Over The Lazy Dog"
            ,c("S16", DRILL)
            ,c("D15", TWIST)
        ),
        s("15A5", "Visual Dream II (In Fiction)"
        ),
        s("15A1", "BSPower Explosion"
            ,c("S21", DRILL, BRACKET)
            ,c("D20", DRILL)
            ,c("D23", DRILL, BRACKET)
        ),
        s("1542", "Escape"
            ,c("S15", DRILL, BRACKET)
            ,c("S18", DRILL, TWIST)
            ,c("S21", DRILL, BRACKET)
            ,c("D19", DRILL, BRACKET)
            ,c("D22", DRILL, BRACKET)
        ),
        s("15D2", "Shub Sothoth REMIX"
        ),
        s("1151", "Night Duty"
            ,c("S17", GIMMICK)
            ,c("S19", GIMMICK, TWIST)
            ,c("D15", GIMMICK)
            ,c("D20", GIMMICK, TWIST)
        ),
        s("1645", "Fly High"
            ,c("S18", GIMMICK, BRACKET)
            ,c("S20", TWIST, SIDEHALF)
            ,c("D19", GIMMICK, BRACKET)
        ),
        s("1647", "HANN"
            ,c("S16", GIMMICK)
            ,c("D17", GIMMICK, TWIST)
        ),
        s("1649", "Nekkoya"
            ,c("S15", SIDEHALF)
            ,c("S17", GIMMICK, SIDEHALF)
            ,c("D15", SIDEHALF)
            ,c("D18", GIMMICK, TWIST)
        ),
        s("1601", "Wedding Crashers"
            ,c("S21", TWIST)
        ),
        s("1603", "Obliteration"
            ,c("S17", DRILL, GIMMICK)
            ,c("D15", BRACKET)
            ,c("D19", GIMMICK)
        ),
        s("1608", "I Want U"
            ,c("S19", TWIST)
            ,c("D21", TWIST)
        ),
        s("1615", "Nyarlathotep"
            ,c("S16", TWIST, SIDEHALF)
            ,c("S18", DRILL)
            ,c("S21", DRILL, TWIST)
            ,c("D16", SIDEHALF)
            ,c("D20", SIDEHALF)
            ,c("D23", DRILL, SIDEHALF)
        ),
        s("1618", "Skeptic"
            ,c("S18", BRACKET)
            ,c("S22", BRACKET)
            ,c("D22", GIMMICK, TWIST)
        ),
        s("1620", "Percent X"
            ,c("S19", GIMMICK, TWIST)
        ),
        s("1622", "Le Grand Rouge"
        ),
        s("1624", "Macaron Day"
            ,c("S17", GIMMICK)
            ,c("D18", GIMMICK)
        ),
        s("1626", "Poseidon"
            ,c("S20", GIMMICK, TWIST)
            ,c("D22", GIMMICK, TWIST)
        ),
        s("1628", "VANISH"
            ,c("S17", GIMMICK)
            ,c("S20", GIMMICK)
            ,c("D18", GIMMICK)
            ,c("D22", GIMMICK)
        ),
        s("1630", "Kimchi Fingers"
            ,c("D16", TWIST)
        ),
        s("16D0", "Desaparecer REMIX"
            ,c("S22", DRILL, TWIST)
            ,c("D19", SIDEHALF)
            ,c("D23", SIDEHALF)
        ),
        s("16F0", "Nyarlathotep SHORT CUT"
            ,c("S22", BRACKET)
        ),
        s("16F1", "Wedding Crashers SHORT CUT"
        ),
        s("1650", "I'm so sick"
            ,c("S17", GIMMICK)
            ,c("D18", GIMMICK)
            ,c("D20", TWIST)
        ),
        s("1651", "BOOMERANG"
            ,c("S17", GIMMICK, BRACKET)
            ,c("D16", GIMMICK)
            ,c("D20", GIMMICK, BRACKET)
        ),
        s("1655", "BBoom BBoom"
            ,c("S15", GIMMICK, BRACKET)
            ,c("D15", BRACKET, SIDEHALF)
            ,c("D18", GIMMICK)
        ),
        s("1659", "LOVE SCENARIO"
            ,c("S17", GIMMICK)
            ,c("D19", GIMMICK)
        ),
        s("1662", "VERY NICE"
            ,c("S17", GIMMICK)
            ,c("D15", GIMMICK, SIDEHALF)
            ,c("D18", GIMMICK)
        ),
        s("1663", "GOOD BYE"
            ,c("S17", TWIST, GIMMICK)
            ,c("D16", SIDEHALF)
            ,c("D18", SIDEHALF)
            ,c("D21", TWIST)
        ),
        s("1668", "Club Night"
            ,c("S18", DRILL, BRACKET)
            ,c("S22", TWIST, BRACKET)
            ,c("D21", DRILL, BRACKET)
            ,c("D23", GIMMICK, BRACKET)
        ),
        s("1670", "86"
            ,c("S16", GIMMICK)
            ,c("S20", GIMMICK, BRACKET)
            ,c("D18", GIMMICK)
            ,c("D21", GIMMICK, BRACKET)
        ),
        s("1678", "Imagination"
            ,c("S15", TWIST)
            ,c("S17", BRACKET)
            ,c("S19", DRILL, TWIST)
            ,c("D16", SIDEHALF)
            ,c("D21", DRILL, SIDEHALF)
        ),
        s("1679", "Black Swan"
            ,c("S16", DRILL)
            ,c("S19", DRILL, TWIST)
            ,c("D22", DRILL, SIDEHALF)
        ),
        s("1680", "Obelisque"
            ,c("D15", SIDEHALF)
            ,c("D17", TWIST)
        ),
        s("1690", "Loki"
            ,c("S16", GIMMICK)
            ,c("S21", GIMMICK, BRACKET)
            ,c("D19", GIMMICK)
        ),
        s("1691", "Dement"
            ,c("S15", TWIST, BRACKET)
        ),
        s("16E0", "BBoom BBoom FULL SONG"
            ,c("S17", GIMMICK)
            ,c("D18", GIMMICK)
        ),
        s("305", "An Interesting View"
        ),
        s("304", "With My Lover"
        ),
        s("913", "Radetzky Can Can"
            ,c("S20", BRACKET)
            ,c("D22", GIMMICK, BRACKET)
        ),
        s("C07", "Jump"
            ,c("S16", BRACKET)
        ),
        s("F32", "Ugly duck Toccata REMIX"
        ),
        s("D36", "Banya-P Classic Remix REMIX"
            ,c("S15", TWIST, BRACKET)
            ,c("S21", GIMMICK)
            ,c("D16", GIMMICK)
            ,c("D22", GIMMICK)
        ),
        s("1642", "The Little Prince"
        ),
        s("1667", "Boong Boong"
            ,c("S16", GIMMICK)
        ),
        s("1644", "Timing"
            ,c("S17", GIMMICK)
            ,c("D18", GIMMICK)
        ),
        s("1625", "Ice of Death"
            ,c("S17", GIMMICK)
            ,c("D21", GIMMICK)
        ),
        s("1685", "Xeroize"
            ,c("S21", TWIST)
            ,c("D22", GIMMICK)
        ),
        s("1694", "Nihilism - Another Ver.-"
        ),
        s("16F7", "86 FULL SONG"
            ,c("S21", GIMMICK, BRACKET)
            ,c("D23", GIMMICK, BRACKET)
        ),
        s("16F3", "XX OPENING SHORT CUT"
            ,c("S16", GIMMICK)
            ,c("D17", GIMMICK, SIDEHALF)
        ),
        s("1660", "Time for the moon night"
            ,c("S18", SIDEHALF)
        ),
        s("1605", "1949"
            ,c("D21", DRILL)
        ),
        s("1652", "Gashina"
            ,c("S18", BRACKET)
            ,c("D19", BRACKET)
        ),
        s("1664", "YOU AND I"
            ,c("S21", BRACKET)
        ),
        s("16C5", "Allegro Con Fuoco FULL SONG"
        ),
        s("16D3", "Meteo5cience REMIX"
            ,c("S18", TWIST)
            ,c("S21", BRACKET)
            ,c("D22", BRACKET, SIDEHALF)
        ),
        s("1681", "Rage of Fire"
            ,c("S18", GIMMICK, TWIST)
        ),
        s("1658", "Starry Night"
            ,c("D17", TWIST)
        ),
        s("1676", "Conflict"
            ,c("S22", GIMMICK)
            ,c("D21", GIMMICK)
        ),
        s("16A0", "Can-can ~Orpheus in The Party Mix~"
            ,c("D18", GIMMICK)
            ,c("D23", DRILL, BRACKET)
        ),
        s("16A1", "Papasito feat. KuTiNA"
            ,c("S15", BRACKET)
            ,c("D16", BRACKET)
            ,c("D19", BRACKET)
        ),
        s("16A2", "Fires of Destiny"
            ,c("D22", DRILL, BRACKET)
        ),
        s("16A3", "The End of the World ft. Skizzo"
            ,c("S20", BRACKET)
            ,c("D17", DRILL, SIDEHALF)
            ,c("D21", GIMMICK, BRACKET)
        ),
        s("16A4", "Forgotten Vampire"
            ,c("D17", SIDEHALF)
        ),
        s("16E3", "Gashina FULL SONG"
            ,c("S15", TWIST)
            ,c("D21", BRACKET)
        ),
        s("16F5", "Poseidon SHORT CUT"
            ,c("S18", DRILL, GIMMICK)
            ,c("D21", GIMMICK, SIDEHALF)
        ),
        s("1646", "Black Cat"
            ,c("S17", GIMMICK)
            ,c("D16", SIDEHALF)
            ,c("D18", GIMMICK, BRACKET)
        ),
        s("1665", "King of Sales"
            ,c("S21", GIMMICK)
            ,c("D19", SIDEHALF)
            ,c("D23", BRACKET)
        ),
        s("16E4", "Starry Night FULL SONG"
        ),
        s("1686", "Rising Star"
            ,c("S17", SIDEHALF)
            ,c("D19", TWIST)
        ),
        s("1635", "Tantanmen"
            ,c("S20", GIMMICK)
        ),
        s("1675", "Friend"
            ,c("S21", GIMMICK)
        ),
        s("1616", "Heart Rabbit Coaster"
        ),
        s("1643", "Full Moon"
            ,c("D20", DRILL, SIDEHALF)
        ),
        s("1695", "Phalanx"
            ,c("S15", TWIST)
            ,c("S22", BRACKET)
            ,c("D21", BRACKET)
            ,c("D22", TWIST)
        ),
        s("1631", "Orbit Stabilizer"
        ),
        s("1623", "Carmen Bus"
            ,c("S18", DRILL)
            ,c("S21", TWIST, SIDEHALF)
            ,c("D22", DRILL)
        ),
        s("16F2", "Can-can ~Orpheus in The Party Mix~ SHORT CUT"
            ,c("D23", DRILL)
        ),
        s("1629", "Tales of Pumpnia"
            ,c("S16", GIMMICK)
            ,c("S19", GIMMICK)
            ,c("D17", GIMMICK)
            ,c("D20", GIMMICK, BRACKET)
        ),
        s("1671", "CROSS SOUL"
        ),
        s("16D4", "Prime Time REMIX"
            ,c("S15", TWIST)
        ),
        s("1674", "Lala"
            ,c("S15", BRACKET)
            ,c("D16", GIMMICK, BRACKET)
        ),
        s("16D8", "HANN (Alone) FULL SONG"
            ,c("S18", TWIST)
        ),
        s("1693", "Your Mind"
            ,c("S17", BRACKET)
            ,c("S21", BRACKET)
            ,c("D18", BRACKET)
            ,c("D23", BRACKET)
        ),
        s("1634", "Wicked Legend"
            ,c("S15", DRILL)
            ,c("S19", GIMMICK, TWIST)
            ,c("D18", GIMMICK)
            ,c("D21", GIMMICK, TWIST)
        ),
        s("1656", "Rooftop"
            ,c("D15", BRACKET)
            ,c("D20", BRACKET)
        ),
        s("1653", "Adios (KPOP)"
            ,c("S15", TWIST)
            ,c("D15", TWIST, BRACKET)
            ,c("D20", BRACKET)
        ),
        s("1673", "After a thousand years"
        ),
        s("1657", "BUNGEE (Fall in Love)"
            ,c("S17", TWIST)
        ),
        s("1654", "HIT"
            ,c("S16", BRACKET)
            ,c("S19", TWIST, BRACKET)
            ,c("D18", TWIST, BRACKET)
            ,c("D21", BRACKET)
        ),
        s("1607", "JANUS"
            ,c("D22", SIDEHALF)
        ),
        s("1617", "Lepton Strike"
            ,c("S19", GIMMICK)
            ,c("S22", BRACKET)
            ,c("D21", GIMMICK, BRACKET)
            ,c("D23", GIMMICK, BRACKET)
        ),
        s("1684", "Uranium"
            ,c("S16", DRILL, TWIST)
            ,c("S22", TWIST)
            ,c("D17", DRILL)
        ),
        s("1648", "Snapping"
            ,c("D18", TWIST)
        ),
        s("1602", "Switronic"
            ,c("S18", GIMMICK)
            ,c("D19", GIMMICK, TWIST)
            ,c("D22", GIMMICK, TWIST)
        ),
        s("16F6", "Switronic SHORT CUT"
            ,c("S18", GIMMICK)
            ,c("D18", GIMMICK, SIDEHALF)
        ),
        s("1604", "Transacaglia in G-minor"
        ),
        s("1669", "Indestructible"
            ,c("S22", BRACKET)
            ,c("D22", BRACKET, SIDEHALF)
        ),
        s("16E7", "VERY NICE FULL SONG"
            ,c("S19", GIMMICK, TWIST)
        ),
        s("1683", "Cycling!"
            ,c("D16", BRACKET)
        ),
        s("1661", "Bon Bon Chocolat"
            ,c("D18", BRACKET)
        ),
        s("16E1", "I’m so sick FULL SONG"
            ,c("S18", TWIST)
            ,c("D20", TWIST)
        ),
        s("1609", "District 1"
            ,c("D21", TWIST)
        ),
        s("1627", "Adrenaline Blaster"
            ,c("S21", TWIST)
            ,c("D22", TWIST)
        ),
        s("1677", "Danger & Danger"
            ,c("D23", TWIST)
        ),
        s("1696", "POINT ZERO 2"
            ,c("S21", TWIST, BRACKET)
            ,c("D22", BRACKET)
        ),
        s("1682", "Dual Racing <RED vs BLUE>"
            ,c("S16", GIMMICK)
            ,c("S18", GIMMICK)
            ,c("S20", TWIST)
            ,c("D22", GIMMICK)
        ),
        s("16D5", "Fire Noodle Challenge REMIX"
            ,c("S15", TWIST)
        ),
        s("16E6", "Time for the moon night FULL SONG"
        ),
        s("1666", "Gotta Go"
            ,c("S15", GIMMICK)
            ,c("S17", GIMMICK, TWIST)
            ,c("D16", GIMMICK)
            ,c("D18", GIMMICK, TWIST)
            ,c("D20", GIMMICK, BRACKET)
        ),
        s("1619", "Iolite Sky"
            ,c("S20", TWIST)
            ,c("S22", GIMMICK, BRACKET)
            ,c("D17", DRILL)
            ,c("D21", GIMMICK, BRACKET)
        ),
        s("1621", "La Cinquantaine"
            ,c("S22", TWIST, BRACKET)
            ,c("D16", SIDEHALF)
        ),
        s("1672", "Broken Karma (PIU Edit)"
            ,c("S21", DRILL)
            ,c("D20", SIDEHALF)
        ),
        s("1687", "Cutie Song"
            ,c("S15", BRACKET)
        ),
        s("16E8", "GOOD BYE FULL SONG"
        ),
        s("16D9", "NEKKOYA FULL SONG"
            ,c("S19", TWIST)
        ),
        s("16F4", "I Want U SHORT CUT"
            ,c("S19", TWIST)
            ,c("D21", SIDEHALF)
        ),
        s("1641", "Jogging"
            ,c("D22", TWIST)
        ),
        s("1632", "DESTRUCIMATE"
            ,c("D19", DRILL, SIDEHALF)
        ),
        s("1633", "Clematis Rapsodia"
            ,c("S22", BRACKET)
            ,c("D16", SIDEHALF)
            ,c("D23", BRACKET)
        ),
        s("1636", "Stardream"
            ,c("S21", DRILL, BRACKET)
            ,c("D15", BRACKET)
            ,c("D19", SIDEHALF)
            ,c("D22", DRILL)
        ),
        s("1688", "Headless Chicken"
            ,c("S19", DRILL, TWIST)
            ,c("S21", BRACKET)
            ,c("D17", DRILL, TWIST)
            ,c("D21", TWIST)
        ),
        s("1689", "Over the Horizon"
            ,c("D18", SIDEHALF)
        ),
        s("1692", "Houseplan"
            ,c("S17", TWIST)
            ,c("D18", TWIST)
        ),
        s("16F9", "Baroque Virus FULL SONG"
            ,c("S16", GIMMICK)
            ,c("S21", DRILL, TWIST)
            ,c("D23", DRILL)
        ),
        s("16D1", "ERRORCODE: 0 REMIX"
        ),
        s("1014", "Twist King"
        ),
        s("F80", "In Your Fantasy"
        ),
        s("1139", "Energy FULL SONG"
        ),
        s("1111", "Ring Ding Dong"
        ),
        s("1137", "Ring Ding Dong FULL SONG"
        ),
        s("1227", "Deadbeat Boyfriend"
        ),
        s("1087", "B.P.M. Collection 1(Auditions)"
        ),
        s("1085", "B.P.M. Collection 2(Solitaries)"
        ),
        s("1070", "B.P.M. Collection 3(Pumpts)"
        ),
        s("1093", "B.P.M. Collection 4(etc.Mix)"
        ),
        s("F19", "Bad Character"
        ),
        s("F21", "Breakin' Love"
        ),
        s("F09", "Chocolate"
        ),
        s("F20", "U"
        ),
        s("F08", "Only You"
        ),
        s("F07", "I am Your Girl"
        ),
        s("F18", "Come On!"
        ),
        s("1130", "K-POP Boy Group RMX REMIX"
        ),
        s("1697", "BRAIN POWER"
            ,c("S18", GIMMICK)
            ,c("S22", GIMMICK, TWIST)
            ,c("D20", GIMMICK)
        ),
        s("16D7", "Full Moon FULL SONG"
        ),
        s("1702", "God Mode 2.0"
            ,c("S21", TWIST)
            ,c("D20", GIMMICK)
            ,c("D22", TWIST)
        ),
        s("1698", "Life is PIANO"
            ,c("S19", GIMMICK, TWIST)
            ,c("D18", SIDEHALF)
            ,c("D21", GIMMICK)
        ),
        s("16A7", "The Reverie"
            ,c("S15", DRILL)
            ,c("S21", BRACKET)
            ,c("D21", TWIST, BRACKET)
        ),
        s("DB54", "2nd Hidden Remix REMIX"
        ),
        s("F52", "Adios FULL SONG"
        ),
        s("1640", "Crossing Delta"
            ,c("D21", TWIST, BRACKET)
            ,c("D23", BRACKET)
        ),
        s("16A5", "Harmagedon"
        ),
        s("1637", "Slapstick Parfait"
        ),
        s("D25", "Bust Back"
        ),
        s("D11", "For You"
        ),
        s("16A6", "Repentance"
            ,c("S19", BRACKET)
            ,c("S22", GIMMICK, BRACKET)
            ,c("D17", GIMMICK)
            ,c("D20", GIMMICK)
            ,c("D23", GIMMICK, BRACKET)
        ),
        s("1613", "Sugar Plum"
            ,c("S15", BRACKET)
            ,c("S18", GIMMICK, BRACKET)
            ,c("D20", GIMMICK, BRACKET)
        ),
        s("1614", "Telling Fortune Flower"
            ,c("S21", GIMMICK)
            ,c("D23", GIMMICK)
        ),
        s("1699", "GLORIA"
            ,c("S17", BRACKET)
            ,c("S20", BRACKET)
            ,c("D16", SIDEHALF)
            ,c("D21", BRACKET)
        ),
        s("1718", "Kokugen Kairou Labyrinth"
            ,c("D18", DRILL, SIDEHALF)
        ),
        s("16C9", "Papasito FULL SONG"
            ,c("S19", TWIST)
            ,c("S22", GIMMICK, BRACKET)
            ,c("D21", BRACKET)
            ,c("D23", GIMMICK, BRACKET)
        ),
        s("1638", "Paved Garden"
            ,c("S22", BRACKET)
        ),
        s("1639", "Pop Sequence"
            ,c("S15", BRACKET)
            ,c("SP18", GIMMICK)
            ,c("S21", BRACKET)
            ,c("D19", BRACKET)
            ,c("DP22", GIMMICK, BRACKET)
            ,c("D23", BRACKET)
        ),
        s("D12", "Snow Dream"
        ),
        s("1705", "Ultimatum"
        ),
        s("16B1", "Mopemope"
            ,c("S15", TWIST, BRACKET)
            ,c("S17", TWIST, BRACKET)
            ,c("S20", DRILL, GIMMICK)
            ,c("D16", BRACKET, SIDEHALF)
            ,c("D19", BRACKET)
            ,c("D23", GIMMICK)
        ),
        s("16B2", "Re：End of a Dream"
            ,c("S15", BRACKET)
        ),
        s("16B3", "Cross Ray"
            ,c("S19", GIMMICK)
            ,c("S22", GIMMICK)
            ,c("D23", GIMMICK)
        ),
        s("16B4", "Cygnus"
            ,c("S19", GIMMICK)
            ,c("S22", GIMMICK)
            ,c("D21", GIMMICK)
        ),
        s("16B5", "Tropicanic"
            ,c("S19", TWIST)
            ,c("D21", TWIST)
        ),
        s("16B6", "Paradoxx SHORT CUT"
        ),
        s("16B7", "Brown Sky REMIX"
        ),
        s("16B8", "GOOD NIGHT FULL SONG"
            ,c("S18", GIMMICK)
            ,c("S21", GIMMICK)
        )
    );

}
