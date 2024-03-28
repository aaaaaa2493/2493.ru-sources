package ru.vt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vt.dao.MixRepository;
import ru.vt.dao.VersionRepository;
import ru.vt.entities.piudb.Mix;
import ru.vt.entities.piudb.Mix.MixValues;
import ru.vt.entities.piudb.Version;

import java.util.Comparator;
import java.util.List;

@Service
public class MixService {

    @Autowired
    MixRepository mixRepo;

    @Autowired
    VersionRepository versionRepo;

    // do not overwrite
    volatile public static Mix latestMix = null;
    volatile public static Version latestVersion = null;

    public List<Mix> getAllMixes() {
        return mixRepo.findAll();
    }

    public Mix get(int id) {
        return mixRepo.findById(id).get();
    }

    public Mix get(MixValues mix) {
        return get(mix.mixId);
    }

    public Mix getLatestMix() {
        if (latestMix == null) {
            latestMix = mixRepo.findAll()
                    .stream()
                    .max(Comparator.comparingInt(Mix::getSortOrder))
                    .get();
        }
        return latestMix;
    }

    public Version getLatestVersion() {
        if (latestVersion == null) {
            latestVersion = versionRepo.findAll()
                .stream()
                .max(Comparator.comparingInt(Version::getSortOrder))
                .get();
        }
        return latestVersion;
    }

}
