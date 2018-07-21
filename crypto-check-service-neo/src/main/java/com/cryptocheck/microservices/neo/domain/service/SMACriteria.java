package com.cryptocheck.microservices.neo.domain.service;

import java.io.Serializable;

public class SMACriteria implements Serializable {
    private static final long serialVersionUID = -6016468693221822352L;
    private int period;
    private int timeFrame;

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(int timeFrame) {
        this.timeFrame = timeFrame;
    }
}
