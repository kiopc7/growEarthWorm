package edu.grow;

import android.app.Application;

public class Option extends Application {

    private long money = 0;
    private long touchmoney;
    private long upgrademoney;
    private  long automoney;

    public long getmoney() {
        return money;
    }

    public void setmoney(long money) {
        this.money = money;
    }

    public long getupgrademoney() {
        return upgrademoney;
    }
    public void setupgrademoney(long upgrademoney) {
        this.upgrademoney = upgrademoney;

    }

    public long getTouchmoney() {
        return touchmoney;
    }

    public void setTouchmoney(long touchmoney) {
        this.touchmoney = touchmoney;
    }

    public  long getAutomoney() {
        return automoney;
    }

    public void setAutomoney( long automoney) {
        this.automoney = automoney;
    }


}
