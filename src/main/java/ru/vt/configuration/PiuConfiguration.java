package ru.vt.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import ru.vt.entities.piudb.SongTitle;
import ru.vt.entities.pumpking.PumpkingData;
import ru.vt.services.MixService;
import ru.vt.services.SongService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Configuration
public class PiuConfiguration {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    SongService songService;

    @Autowired
    MixService mixService;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        var songs = songService.getAllSongs();
        log.debug("All songs loaded");

        for (var s : songs) {
            try {
                System.out.println(s);
            } catch (Exception ex) {
                System.out.println("Failed to print song id=" + s.getSongId());
                throw ex;
            }
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
            List<String> songCards = s.getAllCards();
            if (songCards.size() > 1) {
                System.out.println(songName + " " + s.getIdentifier() + "(" + s.getCard() + ")");
                songCards.forEach(System.out::println);
                System.out.println();
            }
        }
    }

    @Bean
    public PumpkingData getPumpkingResults() throws IOException {
        var mapper = WebSocketConfiguration.objectMapper();

        String results = Files.readString(Path.of("db/pumpking/pumpking.json"));

        PumpkingData data = mapper.readValue(results, PumpkingData.class);

        String idToName = Files.readString(Path.of("db/pumpking/ids.json"));

        TypeReference<Map<String, String>> typeRef = new TypeReference<>() {};
        Map<String, String> idToNameMap = mapper.readValue(idToName, typeRef);
        data.setIdToName(idToNameMap);

        log.debug("Latest Mix: " + mixService.getLatestMix());

        songService.enrichPumpkingData(data);
        return data;
    }

}
