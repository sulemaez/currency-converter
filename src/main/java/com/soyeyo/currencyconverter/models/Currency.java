package com.soyeyo.currencyconverter.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Currency")
public class Currency {

    @Id
    private String name;
    private double valueInUsd;

    public Currency(){

    }

    public Currency(String name, double valueInUsd) {
        this.name = name;
        this.valueInUsd = valueInUsd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValueInUsd() {
        return valueInUsd;
    }

    public void setValueInUsd(double valueInUsd) {
        this.valueInUsd = valueInUsd;
    }


    @Override
    public String toString() {
        return "Currency("+
                "name : " + name +"\n"+
                " valueInUsd" + valueInUsd +
                ")";
    }

}
