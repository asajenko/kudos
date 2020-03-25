package com.asajenko.kudos.model;

import com.asajenko.kudos.service.KudosPeriodType;

import javax.persistence.*;

@Entity
public class KudosPeriod {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToOne
    private Kudos kudos;
    @ManyToOne
    private Period period;
    @Column
    private KudosPeriodType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Kudos getKudos() {
        return kudos;
    }

    public void setKudos(Kudos kudos) {
        this.kudos = kudos;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public KudosPeriodType getType() {
        return type;
    }

    public void setType(KudosPeriodType type) {
        this.type = type;
    }
}
