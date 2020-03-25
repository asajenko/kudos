package com.asajenko.kudos.repository;

import com.asajenko.kudos.model.Period;
import org.springframework.data.repository.CrudRepository;

public interface PeriodRepository extends CrudRepository<Period, Long> {

    public Period findPeriodByStopTimeIsNull();
}
