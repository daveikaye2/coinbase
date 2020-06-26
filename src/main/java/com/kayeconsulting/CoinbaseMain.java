package com.kayeconsulting;

public class CoinbaseMain {

    public static void main(String[] args) {
        BtcPriceMonitor btcPriceMonitor = new BtcPriceMonitor();
        btcPriceMonitor.monitor();
    }

}
