package ru.vt.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vt.dao.SongRepository;
import ru.vt.dto.SongDto;
import ru.vt.entities.korean.KoreanData;
import ru.vt.entities.piudb.Chart;
import ru.vt.entities.piudb.Mix;
import ru.vt.entities.piudb.Mix.MixValues;
import ru.vt.entities.piudb.Song;
import ru.vt.utils.Memorizer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static ru.vt.entities.piudb.Mix.MixValues.XX;

@Service
public class SongService {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    SongRepository repo;

    @Autowired
    MixService mixService;

    private final List<Song> allSongs = new ArrayList<>();


    public List<Song> getAllSongs() {
        if (allSongs.isEmpty()) {
            synchronized (allSongs) {
                if (allSongs.isEmpty()) {
                    generateAllSongs();
                }
            }
        }
        return new ArrayList<>(allSongs);
    }

    @Autowired
    ResourceExistsService existsService;

    public String getCardBigForMix(Song song, Mix mix) {
        while (mix.enumValue().ordinal() >= XX.ordinal()) {
            var mixCardBig = "/img/card_big/" + mix + "/"  + song.getIdentifier() + ".png";
            if (existsService.resourceExists(mixCardBig)) {
                return mixCardBig;
            }
            mix = mix.getParentMix();
        }
        return song.getCardBig();
    }

    private void generateAllSongs() {
        List<Integer> ids = new ArrayList<>();
        List<Song> songs = repo.findAll();

        log.debug("Songs total found: " + songs.size());
        songs = songs.stream().filter(e -> e.getMinMix() != null || e.getIdentifier() != null).toList();
        log.debug("Songs after removing Infinity: " + songs.size());

        for (var s : songs) {
            if (!ids.contains(s.getSongId())) {
                ids.add(s.getSongId());
                allSongs.add(s);
            } else {
                System.out.println("Duplicate found:\n" + s + "\n\nOriginal:\n" +
                        allSongs.stream().filter(ss -> ss.getSongId() == s.getSongId()).toList().get(0) + "\n\n\n\n");
            }
        }

        Mix latest = mixService.getLatestMix();

        log.debug("Songs after removing dups: " + allSongs.size());
        log.debug("Songs in " + latest + ": " + allSongs.stream().filter(s -> s.hasChartsForMix(latest)).count());
        log.debug("Charts in " + latest + ": " + allSongs.stream().mapToInt(s -> s.getChartsForMix(latest).size()).sum());
    }

    private final Function<Integer, List<SongDto>> songMemo = Memorizer.memorize(mixId -> {
        List<SongDto> songsForMix = new ArrayList<>();
        Mix mix = mixService.get(mixId);

        for (var song: getAllSongs()) {
            List<Chart> charts = song.getChartsForMix(mix);
            if (charts.isEmpty()) {
                continue;
            }
            var card = getCardBigForMix(song, mix);
            songsForMix.add(new SongDto(song, mix, card));
        }

        return songsForMix;
    });

    public List<SongDto> getAllSongsForMix(int mixId) {
        return songMemo.apply(mixId);
    }

    public List<SongDto> getAllSongsForMix(MixValues mix) {
        return getAllSongsForMix(mix.mixId);
    }

    public void enrichChartStyles() {
        songData:
        for (var songData : KoreanData.data) {
            for (var song : getAllSongs()) {
                if (songData.getSongId().equals(song.getIdentifier())) {
                    chartData:
                    for (var chartData : songData.getCharts()) {
                        for (var chart : song.getCharts()) {
                            if (!chart.existInMix(mixService.get(XX))) {
                                continue;
                            }
                            if (chart.getDifficultyForMix(mixService.get(XX)).getName().equals(chartData.getDifficulty())) {
                                if (chart.getStyles() != null && chart.getStyles().size() != 0) {
                                    throw new IllegalStateException(
                                        "Cannot set the style of the chart more than once\n"
                                            + song + "\n\n" + chart);
                                }
                                chart.setStyles(chartData.getStyles());
                                continue chartData;
                            }
                        }
                        throw new IllegalStateException("Can't find chart " +
                            chartData.getDifficulty() + " in song\n" + song);
                    }
                    continue songData;
                }
            }

            // No such song in Mix
            //throw new IllegalStateException("Can't find song "
            //    + songData.getSongId() + " " + songData.getName());
        }
        log.debug("Chart styles Analyzed OK");
    }


}
