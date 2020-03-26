package com.asajenko.kudos.service;

import com.asajenko.kudos.model.Kudos;
import com.asajenko.kudos.model.Period;
import com.asajenko.kudos.repository.KudosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class KudosService {

    private KudosRepository kudosRepository;

    @Autowired
    public KudosService(KudosRepository kudosRepository) {
        this.kudosRepository = kudosRepository;
    }

    public List<Kudos> getAllKudos() {
        return (List<Kudos>) kudosRepository.findAll();
    }

    public List<Kudos> getAllKudosFrom(String who) {
        return kudosRepository.findByWho(who);
    }

    public List<Kudos> getAllKudosTo(String to) {
        return kudosRepository.findByTo(to);
    }

    public List<Kudos> getAllKudosForActualInterval(Period actualPeriod) {
        return kudosRepository.findByWhenAfter(actualPeriod.getStartTime());
    }

    public List<Kudos> getAllKudosForPeriodTime(Period period) {
        return kudosRepository.findByWhenBetween(period.getStartTime(), period.getStopTime());
    }

    public List<Kudos> randomKudos(int count, List<Kudos> kudosList) {
        //todo: stop random when kudosList.size < count and check kudosList is not null
        Random rand = new Random();
        List<Kudos> newKudosList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int randomIndex = rand.nextInt(kudosList.size());
            newKudosList.add(kudosList.get(randomIndex));
            kudosList.remove(randomIndex);
        }
        return newKudosList;
    }
}
