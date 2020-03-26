package com.asajenko.kudos.service;

import com.asajenko.kudos.model.Kudos;
import com.asajenko.kudos.model.KudosPeriod;
import com.asajenko.kudos.model.Period;
import com.asajenko.kudos.repository.KudosPeriodRepository;
import com.asajenko.kudos.repository.KudosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class KudosService {

    private final KudosRepository kudosRepository;
    private final KudosPeriodRepository kudosPeriodRepository;

    @Autowired
    public KudosService(KudosRepository kudosRepository, KudosPeriodRepository kudosPeriodRepository) {
        this.kudosRepository = kudosRepository;
        this.kudosPeriodRepository = kudosPeriodRepository;
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
        if (kudosList != null && !kudosList.isEmpty()) {
            List<Kudos> newKudosList = new ArrayList<>();
            if (count >= kudosList.size()) {
                newKudosList = List.copyOf(kudosList);
                kudosList.clear();
                return newKudosList;
            }
            Random rand = new Random();
            for (int i = 0; i < count; i++) {
                int randomIndex = rand.nextInt(kudosList.size());
                newKudosList.add(kudosList.get(randomIndex));
                kudosList.remove(randomIndex);
            }
            return newKudosList;
        }
        return null;
    }

    public void saveKudosPeriod(Period period, List<Kudos> kudos, KudosPeriodType type) {
        Optional.ofNullable(kudos).ifPresent(l -> l.forEach(k -> kudosPeriodRepository.save(new KudosPeriod(k, period, type))));
    }
}
