package com.asajenko.kudos.service;

import com.asajenko.kudos.model.Kudos;
import com.asajenko.kudos.repository.KudosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public List<Kudos> getAllKudosForActualInterval() {
        //TODO: change timestamp to real interval date start from DB
        return kudosRepository.findByWhenAfter(new Date(1584827543000L));
    }
}
