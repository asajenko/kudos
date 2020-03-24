package com.asajenko.kudos.model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Period {

    private Date startTime;
    private Date stopTime;
    private boolean finish;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
