package com.asajenko.kudos.service;

import com.asajenko.kudos.TmpStorageSingleton;
import com.asajenko.kudos.model.Kudos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KudosService {

    public List<Kudos> getAllKudos() {
        return TmpStorageSingleton.getInstance().getAllKudos();
    }
}
