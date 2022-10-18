package ru.vt.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vt.dao.MixRepository;
import ru.vt.dao.SongRepository;
import ru.vt.dto.ChartResults;
import ru.vt.dto.SongCharts;
import ru.vt.entities.piudb.Chart;
import ru.vt.entities.piudb.Cut.CutValues;
import ru.vt.entities.piudb.Mix;
import ru.vt.entities.piudb.Mix.MixValues;
import ru.vt.entities.piudb.Song;
import ru.vt.entities.piudb.SongTitle;
import ru.vt.entities.pumpking.PumpkingData;
import ru.vt.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SongService {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    SongRepository repo;

    @Autowired
    MixRepository mixRepo;

    private final List<Song> allSongs = new ArrayList<>();
    private final List<ChartResults> pumpkingResults = new ArrayList<>();

    public List<ChartResults> getResults() {
        return new ArrayList<>(pumpkingResults);
    }

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

    private void generateAllSongs() {
        List<Integer> ids = new ArrayList<>();
        List<Song> songs = repo.findAll();

        log.debug("Songs total found: " + songs.size());

        for (var s : songs) {
            if (!ids.contains(s.getSongId())) {
                ids.add(s.getSongId());
                allSongs.add(s);
            }
        }

        log.debug("Songs after removing dups: " + allSongs.size());
    }

    public List<SongCharts> getAllSongsForMix(int mixId) {
        List<SongCharts> songsForMix = new ArrayList<>();
        Mix mix = mixRepo.findById(mixId).get();

        for (var song: getAllSongs()) {
            List<Chart> charts = song.getChartsForMix(mix);
            if (charts.isEmpty()) {
                continue;
            }
            songsForMix.add(new SongCharts(song, charts));
        }

        return songsForMix;
    }

    public void enrichPumpkingData(PumpkingData data) {
        Map<String, String> corrections = new HashMap<>();
        corrections.put("Close Your Eyes", "Close Your Eye");
        corrections.put("Can Can", "Radetzky Can Can");
        corrections.put("Will o' the Wisp", "Will-O-The-Wisp");
        corrections.put("Do It -Reggae Style-", "Do It Reggae Style");
        corrections.put("Flew Far Faster", "FFF");
        corrections.put("Log-In", "Log In");
        corrections.put("Ai, Yurete", "Ai, Yurete...");
        corrections.put("Sudden Romance (PIU Edit)", "Sudden Romance [PIU Edit]");
        corrections.put("Ren'ai Yuusha", "Renai Yuusha");
        corrections.put("Bad &infin; End &infin; Night", "Bad ∞ End ∞ Night");
        corrections.put("Papasito (ft. KuTiNA)", "Papasito feat. KuTiNA");
        corrections.put("The End of the World (ft. Skizzo)", "The End of the World ft. Skizzo");
        corrections.put("2006 Love Song", "2006. LOVE SONG");
        corrections.put("BanYa-P Classic Mix", "Banya-P Classic Remix");
        corrections.put("Canon-D", "Canon D");
        corrections.put("Do You Know That -Old School-", "Do U Know That-Old School");
        corrections.put("Final Audition 3 U.F", "Final Audition 3");
        corrections.put("Final Audition episode 1", "Final Audition Ep. 1");
        corrections.put("Final Audition episode 2-1", "Final Audition Ep. 2-1");
        corrections.put("Final Audition episode 2-2", "Final Audition Ep. 2-2");
        corrections.put("Final Audition episode 2-X", "Final Audition Ep. 2-X");
        corrections.put("Four Seasons of Loneliness", "FOUR SEASONS OF LONELINESS verβ feat. sariyajin");
        corrections.put("Hi-Bi", "Hi Bi");
        corrections.put("Jam o' Beat", "Jam O Beat");
        corrections.put("K.O.A. -Alice in Wonderworld-", "K.O.A : Alice In Wonderworld");
        corrections.put("Love is a Danger Zone 2 (D&G Ver.)", "Love is a Danger Zone pt.2 another");
        corrections.put("Miss's Story", "Miss S' story");
        corrections.put("Pumptris (8Bit ver.)", "Pumptris 8Bit ver.");
        corrections.put("Pumptris (8bit ver.)", "Pumptris 8Bit ver.");
        corrections.put("Tream Vook of the War", "Tream Vook of the war REMIX");
        corrections.put("What Are You Doin'?", "What Are You Doin?");
        corrections.put("X-Tree", "XTREE");
        corrections.put("X-Treme", "X Treme");
        corrections.put("Four Seasons of Loneliness ver B feat. Sariyajin",
                "FOUR SEASONS OF LONELINESS verβ feat. sariyajin");

        Utils.synchronizeOnEmpty(pumpkingResults, () -> {
            List<SongCharts> allSongs = getAllSongsForMix(MixValues.XX.mixId);

            for (var d : data.getData().values().stream().sorted().toList()) {
                String trackName = d.getChart().getTrackName()
                        .replace("[SHORT]", "")
                        .replace("[FULL]", "")
                        .strip();

                String searchableTrackName = corrections.getOrDefault(trackName, trackName)
                        .toLowerCase()
                        .replace(" ", "");

                List<SongCharts> sameTitleAndCut = allSongs.stream()
                        .filter(st -> {
                            Song s = st.getSong();

                            List<String> titles = new ArrayList<>(
                                    s.getSongTitles().stream().map(SongTitle::getTitle).toList());
                            titles.add(s.getName());

                            for (var t : titles) {
                                t = t.replace("Love is a Danger Zone pt. 2",
                                                "Love is a Danger Zone 2")
                                        .replace(" ", "")
                                        .replace("’", "'")
                                        .toLowerCase();

                                if (searchableTrackName.equals(t)) {
                                    return true;
                                }
                            }

                            return false;
                        })
                        .filter(st -> {
                            var duration = d.getChart().getDuration();
                            int cutId = CutValues.fromPumpking(duration).cutId;
                            return st.getSong().getCutId() == cutId;
                        })
                        .toList();

                String fullTrackName = trackName
                        + " " + d.getChart().getChartLabel()
                        + " " + d.getChart().getDuration();

                if (sameTitleAndCut.isEmpty()) {
                    throw new RuntimeException("Can't enrich Pumpking Data " +
                            "- no song candidate of " + fullTrackName);

                } else if (sameTitleAndCut.size() > 1) {
                    throw new RuntimeException("Can't enrich Pumpking Data " +
                            "- multiple song candidates " + fullTrackName);

                } else {
                    SongCharts songCharts = sameTitleAndCut.get(0);
                    String label = d.getChart().getChartLabel().toUpperCase();

                    if (label.contains("COOP")) {
                        label = label.replace("COOP", "C");
                    }

                    List<Chart> chartCandidates = new ArrayList<>();

                    for (var c : songCharts.getCharts()) {
                        if (c.getLastDifficulty().shortname().equals(label)) {
                            chartCandidates.add(c);
                        }
                    }

                    if (chartCandidates.isEmpty()) {
                        throw new RuntimeException("Can't enrich Pumpking Data " +
                                "- no chart candidate of " + fullTrackName);

                    } else if (chartCandidates.size() > 1) {
                        throw new RuntimeException("Can't enrich Pumpking Data " +
                                "- many chart candidates of " + fullTrackName);
                    }

                    Chart chart = chartCandidates.get(0);
                    var results = d.getResults();
                    var bestGrade = d.getBestGradeResults();

                    for (var result : results) {
                        if (bestGrade != null) {
                            for (var bestResult : bestGrade) {
                                if (bestResult.getPlayer().equals(result.getPlayer())) {
                                    result.setGrade(bestResult.getGrade());
                                    result.setScore(bestResult.getScore());
                                    result.setRankMode(bestResult.getRankMode());
                                    break;
                                }
                            }
                        }
                        result.setPlayer(data.getIdToName().get(result.getPlayer()));
                    }

                    pumpkingResults.add(new ChartResults(
                            songCharts.getSong(), chart, results, d.getChart().getDifficulty()));
                }
            }
        });

        for (var songChart : getAllSongsForMix(MixService.latestMix.getMixId())) {
            var song = songChart.getSong();
            var realCharts = songChart.getCharts();
            var pumkingCharts = pumpkingResults.stream()
                    .filter(pr -> pr.getSong().equals(song))
                    .map(ChartResults::getChart)
                    .toList();

            for (var rc : realCharts) {
                if (!pumkingCharts.contains(rc)) {
                    pumpkingResults.add(new ChartResults(song, rc, new ArrayList<>(), 0.0));
                }
            }
        }

        log.debug("Pumpking Analyzed OK");
        // openRequest = indexedDB.open("localforage", 2);
        // transaction = openRequest.result.transaction("keyvaluepairs", "readwrite").objectStore('keyvaluepairs').get('resultsCache');
        // transaction.result
    }



}
