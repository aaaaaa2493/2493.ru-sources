package ru.vt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vt.entities.piudb.Version;

import java.util.List;

@Repository
public interface VersionRepository extends CrudRepository<Version, Integer> {

    @Override
    List<Version> findAll();

}
