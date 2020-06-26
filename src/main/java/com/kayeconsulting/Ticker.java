package com.kayeconsulting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ticker {

    private double price;
    private double bid;
    private double ask;

    @JsonProperty("trade_id")
    private String tradeId;

    @JsonProperty("size")
    private String size;

    @JsonProperty("time")
    private String time;

    @JsonProperty("volume")
    private String volume;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
