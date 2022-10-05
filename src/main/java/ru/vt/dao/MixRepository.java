package ru.vt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vt.entities.piudb.Mix;

import java.util.List;

@Repository
public interface MixRepository extends CrudRepository<Mix, Integer> {

    @Override
    List<Mix> findAll();

}
