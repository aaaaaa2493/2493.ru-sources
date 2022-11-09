package ru.vt.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.vt.entities.piudb.Chart;
import ru.vt.entities.piudb.Song;

import java.util.List;

@Data
public class SongCharts implements Comparable<SongCharts> {
    Song song;
    List<Chart> charts;

    public SongCharts(Song song, List<Chart> charts) {
        this.song = song;
        this.charts = charts;
    }

    @JsonIgnore
    public String getPrint() {
        return song.getName() + " " + song.getIdentifier() + " " + song.getCut();
    }

    @Override
    public int compareTo(SongCharts o) {
        return getPrint().toLowerCase().compareTo(o.getPrint().toLowerCase());
    }
}
