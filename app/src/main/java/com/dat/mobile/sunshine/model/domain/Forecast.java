package com.dat.mobile.sunshine.model.domain;

/**
 * Created by burcoral on 1/4/15.
 */
public class Forecast {
    public String day;
    public String min;
    public String max;
    public String desc;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return
                day + "-" + desc +" - " +  min + '/' + max + '\'' ;
    }
}
