package com.asajenko.kudos.service;

import com.asajenko.kudos.TmpStorageSingleton;
import com.asajenko.kudos.model.Kudos;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SaveKudosService {

    public void saveKudos(Kudos kudos) {
        TmpStorageSingleton tmpStorage = TmpStorageSingleton.getInstance();
        kudos.setCreateDate(new Date());
        tmpStorage.addKudos(kudos);
        System.out.println("Create new kudos : " + kudos.toString());
    }
}
