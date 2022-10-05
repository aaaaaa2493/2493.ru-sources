package ru.vt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vt.entities.piudb.Song;

import java.util.List;

@Repository
public interface SongRepository extends CrudRepository<Song, Integer> {

    @Override
    List<Song> findAll();

}
