package ru.vt.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.vt.entities.piudb.SongCard;
import ru.vt.entities.piudb.SongTitle;
import ru.vt.entities.pumpking.PumpkingData;
import ru.vt.services.MixService;
import ru.vt.services.PumpkingService;
import ru.vt.services.SongService;
import ru.vt.utils.Utils;

import java.io.IOException;
import java.util.List;

import static ru.vt.entities.piudb.Mix.MixValues.Phoenix;

@Configuration
@EnableScheduling
public class PiuConfiguration {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    SongService songService;

    @Autowired
    MixService mixService;

    @Autowired
    PumpkingService pumpkingService;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        var songs = songService.getAllSongs();
        log.debug("After startup");

        for (var s : songs) {
            try {
                System.out.println(s);
                if (s.hasChartsForMix(mixService.get(Phoenix))) {
                    //System.out.println(s.printForMix(mixService.getById(Phoenix)));
                }
                //System.out.println(s.getIdentifier());
            } catch (Exception ex) {
                System.out.println("Failed to print song id=" + s.getSongId());
                throw ex;
            }
        }

        if (true) {
            return;
        }

        for (var s : songs) {
            String songName = s.getName();
            List<String> songTitles = s.getSongTitles().stream()
                    .filter(st -> st.getLanguage().getCode().equals("en")).map(SongTitle::getTitle).toList();

            if (!songTitles.contains(songName) && songTitles.size() > 0) {
                System.out.println(songName);
                for (var st : songTitles) {
                    System.out.println(st);
                }
                System.out.println();
            }
        }

        for (var s: songs) {
            String songName = s.getName();
            List<SongCard> songCards = s.getAllCards();
            if (songCards.size() > 1) {
                System.out.println(songName + " " + s.getIdentifier() + "(" + s.getCard() + ")");
                for (var sc : songCards) {
                    System.out.println(sc.getPath());
                    for (int x = 0; x < sc.getVersions().size(); x++) {
                        System.out.println(sc.getVersions().get(x) + " " + sc.getOperations().get(x));
                    }
                }
                System.out.println();
            }
        }

        /*
        for (var s : songs) {
            var name = s.getName().strip();
            if (s.getCut().isFullSong()) {
                name += " FULL SONG";
            } else if (s.getCut().isShortCut()) {
                name += " SHORT CUT";
            } else if (s.getCut().isRemix()) {
                name += " REMIX";
            }
            System.out.println(
                "s(\"" + s.getIdentifier() + "\", \"" + name + "\"\n" +
                "),"
            );
        }
        */
    }

    @Bean
    public PumpkingData getPumpkingResults() throws IOException {
        PumpkingData data = Utils.readJsonFromFile("db/pumpking/pumpking.json", PumpkingData.class);

        log.debug("Latest Mix: " + mixService.getLatestMix());

        songService.getAllSongs(); // force generating
        songService.enrichChartStyles();

        //pumpkingService.collectPlayersIds();
        //pumpkingService.enrichPumpkingData(data);

        return data;
    }

    @Scheduled(fixedRate = 3600000)  // 3600000 milliseconds = 1 hour
    void updatePumpkingData() throws JsonProcessingException {
        pumpkingService.updatePumpking();
    }

}
