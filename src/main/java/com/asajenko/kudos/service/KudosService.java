package com.asajenko.kudos.service;

import com.asajenko.kudos.model.Kudos;
import com.asajenko.kudos.repository.KudosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
