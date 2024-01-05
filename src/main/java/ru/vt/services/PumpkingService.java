package ru.vt.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import ru.vt.configuration.WebSocketConfiguration;
import ru.vt.dto.ChartDto;
import ru.vt.dto.ChartResultDto;
import ru.vt.dto.ChartResults;
import ru.vt.dto.SongDto;
import ru.vt.entities.piudb.Chart;
import ru.vt.entities.piudb.Cut.CutValues;
import ru.vt.entities.piudb.Mix;
import ru.vt.entities.piudb.Song;
import ru.vt.entities.piudb.SongTitle;
import ru.vt.entities.pumpking.PumpkingData;
import ru.vt.entities.pumpking.PumpkingPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static ru.vt.entities.piudb.Mix.MixValues.Phoenix;
import static ru.vt.entities.piudb.Mix.MixValues.XX;

@Service
@Slf4j
@EnableScheduling
public class PumpkingService {

    @Autowired
    MixService mixService;

    @Autowired
    SongService songService;

    @Value("${2493.pumpking.session}")
    private String pumpkingSession;

    private PumpkingData data;
    private Map<String, PumpkingPlayer> players;
    private boolean initialized = false;

    private final Map<String, ChartResults> pumpkingResults = new HashMap<>();

    public List<ChartResults> getResults() {
        return new ArrayList<>(pumpkingResults.values());
    }

    public String getLatestResultsUpdate() {
        return data.getLastUpdatedOn();
    }

    public List<ChartResultDto> getAllResultsForMix(int mixId) {
        List<ChartResultDto> resultsForMix = new ArrayList<>();
        Mix mix = mixService.get(mixId);

        for (var result : getResults()) {
            if (result.getChart().existInMix(mix)) {
                resultsForMix.add(new ChartResultDto(result, mix));
            }
        }

        return resultsForMix;
    }

    public void enrichPumpkingData(PumpkingData updateData) {
        if (!initialized) {
            return;
        }

        if (this.data == null) {
            this.data = updateData;
        } else {
            this.data.setLastUpdatedOn(updateData.getLastUpdatedOn());
        }

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

        List<SongDto> allSongs = songService.getAllSongsForMix(XX);

        for (var entry : updateData.getData().entrySet().stream().sorted(Map.Entry.comparingByValue()).toList()) {
            var pumpkingChartId = entry.getKey();
            var chartData = entry.getValue();

            String trackName = chartData.getChart().getTrackName()
                .replace("[SHORT]", "")
                .replace("[FULL]", "")
                .strip();

            String searchableTrackName = corrections.getOrDefault(trackName, trackName)
                .toLowerCase()
                .replace(" ", "");

            List<SongDto> sameTitleAndCut = allSongs.stream()
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
                    var duration = chartData.getChart().getDuration();
                    var cutId = CutValues.fromPumpking(duration);
                    return st.getSong().getCut().enumValue() == cutId;
                })
                .toList();

            String fullTrackName = trackName
                + " " + chartData.getChart().getChartLabel()
                + " " + chartData.getChart().getDuration();

            if (sameTitleAndCut.isEmpty()) {
                throw new RuntimeException("Can't enrich Pumpking Data " +
                    "- no song candidate of " + fullTrackName);

            } else if (sameTitleAndCut.size() > 1) {
                throw new RuntimeException("Can't enrich Pumpking Data " +
                    "- multiple song candidates " + fullTrackName);

            } else {
                SongDto songCharts = sameTitleAndCut.get(0);
                String label = chartData.getChart().getChartLabel().toUpperCase();

                if (label.contains("COOP")) {
                    label = label.replace("COOP", "C");
                }

                List<Chart> chartCandidates = new ArrayList<>();

                for (var c : songCharts.getCharts()) {
                    if (c.getDifficulty().getName().equals(label)) {
                        chartCandidates.add(c.getChart());
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
                var results = chartData.getResults();
                var bestGrade = chartData.getBestGradeResults();

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
                    result.setPlayer(players.get(result.getPlayer()).getNickname());
                }

                var pumpkingChartResults = new ChartResults(
                    songCharts.getSong(), chart, results, chartData.getChart().getDifficulty());

                pumpkingResults.put(pumpkingChartId, pumpkingChartResults);
                chart.setResults(pumpkingChartResults);
            }
        }

        // re-add missing charts (there may be less of them so firstly need to delete all of them)
        pumpkingResults.entrySet().removeIf(entry -> entry.getKey().startsWith("!"));

        for (var songChart : songService.getAllSongsForMix(XX)) {
            var song = songChart.getSong();
            var realCharts = songChart.getCharts().stream().map(ChartDto::getChart).toList();
            var pumpkingCharts = pumpkingResults.values().stream()
                .filter(pr -> pr.getSong().equals(song))
                .map(ChartResults::getChart)
                .toList();

            for (var rc : realCharts) {
                if (!pumpkingCharts.contains(rc)) {
                    var pumpkingChartResults = new ChartResults(song, rc, new ArrayList<>(), 0.0);
                    pumpkingResults.put("!" + song.getIdentifier() + rc.getChartId(), pumpkingChartResults);
                    rc.setResults(pumpkingChartResults);
                }
            }
        }

        // Add charts from Phoenix
        for (var songChart : songService.getAllSongsForMix(Phoenix)) {
            var song = songChart.getSong();
            if (song.hasChartsForMix(mixService.get(XX))) {
                continue;
            }
            var realCharts = songChart.getCharts().stream().map(ChartDto::getChart).toList();
            for (var rc : realCharts) {
                var pumpkingChartResults = new ChartResults(song, rc, new ArrayList<>(), 0.0);
                pumpkingResults.put("!" + song.getIdentifier() + rc.getChartId(), pumpkingChartResults);
                rc.setResults(pumpkingChartResults);
            }
        }

        log.debug("Pumpking Analyzed OK: " + updateData.getData().size() + " entries");
    }

    public void collectPlayersIds() throws JsonProcessingException {
        String result = pumpkingRequest("players");
        if (result == null) {
            log.debug("Players analyzed NOT OK: result is null");
            return;
        }
        ObjectMapper mapper = WebSocketConfiguration.objectMapper();
        JsonNode rootNode = mapper.readTree(result);
        JsonNode playersNode = rootNode.get("players");
        players = mapper.convertValue(playersNode, new TypeReference<>() {});
        if (players == null) {
            log.debug("Players analyzed NOT OK: players is null");
        } else {
            log.debug("Players analyzed OK: " + players.size() + " nicknames");
            initialized = true;
        }
    }

    public void updatePumpking() throws JsonProcessingException {
        if (!initialized) {
            return;
        }

        String result = pumpkingRequest("results/best?since=" + data.getLastUpdatedOn());

        if (result == null) {
            if (initialized) {
                log.error("Can't update Pumpking");
            }
            return;
        }

        ObjectMapper mapper = WebSocketConfiguration.objectMapper();
        JsonNode rootNode = mapper.readTree(result);

        if (rootNode.isObject() && rootNode.has("charts")) {
            ObjectNode objectNode = (ObjectNode) rootNode;
            JsonNode chartsNode = objectNode.get("charts");
            objectNode.set("data", chartsNode);
            objectNode.remove("charts");
        }

        if (rootNode.isObject() && rootNode.has("lastUpdatedAt")) {
            ObjectNode objectNode = (ObjectNode) rootNode;
            JsonNode chartsNode = objectNode.get("lastUpdatedAt");
            objectNode.set("lastUpdatedOn", chartsNode);
            objectNode.remove("lastUpdatedAt");
        }

        PumpkingData data = mapper.treeToValue(rootNode, PumpkingData.class);
        enrichPumpkingData(data);
    }

    private String pumpkingRequest(String path) {
        try {
            OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

            Request request = new Request.Builder()
                .url("https://api.pumpking.top/" + path)
                .addHeader("Cookie", "session=" + pumpkingSession)
                .removeHeader("User-Agent")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 YaBrowser/23.7.0.2526 Yowser/2.5 Safari/537.36")
                .build();

            Response response = client.newCall(request).execute();

            if (response.body() == null) {
                return null;
            }
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
