package ru.vt.entities.korean;

import lombok.Data;

import java.util.List;

@Data
public class KoreanSongData {
    String songId;
    String name;
    List<KoreanChartData> charts;

    public KoreanSongData(String songId, String name, List<KoreanChartData> charts) {
        this.songId = songId;
        this.name = name;
        this.charts = charts;
    }
}
