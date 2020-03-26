package com.asajenko.kudos.repository;

import com.asajenko.kudos.model.Kudos;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface KudosRepository extends CrudRepository<Kudos, Long> {

    public List<Kudos> findByWho(String who);
    public List<Kudos> findByTo(String to);
    public List<Kudos> findByWhenAfter(Date whenDate);
    public List<Kudos> findByWhenBetween(Date startDate, Date stopDate);
}
