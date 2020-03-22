package com.asajenko.kudos;

import com.asajenko.kudos.model.Kudos;

import java.util.ArrayList;
import java.util.List;

public class TmpStorageSingleton {

    private static final TmpStorageSingleton instance = new TmpStorageSingleton();
    private List<Kudos> kudosList = new ArrayList<>();

    private TmpStorageSingleton() {}

    public static TmpStorageSingleton getInstance() {
        return instance;
    }

    public void addKudos(Kudos kudos) {
        kudosList.add(kudos);
    }

    public List<Kudos> getAllKudos() {
        return kudosList;
    }
}
