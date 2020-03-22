package com.asajenko.kudos.service;

import com.asajenko.kudos.model.Kudos;
import com.asajenko.kudos.repository.KudosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SaveKudosService {

    private KudosRepository kudosRepository;

    @Autowired
    public SaveKudosService(KudosRepository kudosRepository) {
        this.kudosRepository = kudosRepository;
    }

    public void saveKudos(Kudos kudos) {
        kudos.setWhen(new Date());
        kudosRepository.save(kudos);
        System.out.println("Create new kudos : " + kudos.toString());
    }
}
