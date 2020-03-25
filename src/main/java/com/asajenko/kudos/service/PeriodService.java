package com.asajenko.kudos.service;

import com.asajenko.kudos.model.Period;
import com.asajenko.kudos.repository.PeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PeriodService {

    private PeriodRepository periodRepository;

    @Autowired
    public PeriodService(PeriodRepository periodRepository) {
        this.periodRepository = periodRepository;
    }

    public Period getActualPeriod() {
        return periodRepository.findPeriodByStopTimeIsNull();
    }

    public Period finishPeriod() {
        Period period = getActualPeriod();
        Date now = new Date();
        period.setStopTime(now);
        period.setFinish(true);
        periodRepository.save(period);
        Period newPeriod = new Period();
        newPeriod.setStartTime(now);
        periodRepository.save(newPeriod);
        return period;
    }
}
