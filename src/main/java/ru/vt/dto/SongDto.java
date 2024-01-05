package ru.vt.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.vt.entities.piudb.Mix;
import ru.vt.entities.piudb.Song;
import ru.vt.entities.piudb.SongBpm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class SongDto {
    @JsonIgnore
    final Song song;

    final List<String> artists;
    final SongBpm bpm;
    final String card;
    final String cardBig;
    final String category;
    final String cut;
    final String identifier;
    final String name;
    final int songId;
    final List<ChartDto> charts;

    public SongDto(Song song, Mix mix, String cardBig) {
        this.song = song;
        this.artists = song.getArtists();
        this.bpm = song.getBpm();
        this.card = song.getCard();
        this.cardBig = cardBig;
        this.category = song.getCategory();
        this.cut = song.getCut().getName();
        this.identifier = song.getIdentifier();
        this.name = song.getName();
        this.songId = song.getSongId();

        var charts = new ArrayList<ChartDto>();
        for (var chart : song.getChartsForMix(mix)) {
            charts.add(new ChartDto(chart, mix));
        }
        this.charts = Collections.unmodifiableList(charts);
    }
}
