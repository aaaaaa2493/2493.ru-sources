package ru.vt.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import ru.vt.entities.piudb.Song;
import ru.vt.entities.pumpking.PumpkingData;
import ru.vt.services.MixService;
import ru.vt.services.SongService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Configuration
public class PiuConfuguration {

    @Autowired
    SongService songService;

    @Autowired
    MixService mixService;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("hello world, I have just started up");
        for (Song s : songService.getAllSongs()) {
            var charts = s.getCharts();
            if (!charts.isEmpty()) {
                System.out.println(s);
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

        System.out.println("Latest Mix: " + mixService.getLatestMix());

        songService.enrichPumpkingData(data);
        return data;
    }

}
