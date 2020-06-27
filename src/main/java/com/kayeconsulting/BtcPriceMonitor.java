package com.kayeconsulting;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.coinbasepro.CoinbaseProExchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;

import java.io.IOException;

public class BtcPriceMonitor {

    private final Slack slack;
    private final Exchange coinbasePro;

    public BtcPriceMonitor() {
        this.slack = new Slack();
        this.coinbasePro = ExchangeFactory.INSTANCE.createExchange(CoinbaseProExchange.class);
    }

    public void monitor() {
        try {
            Ticker ticker = coinbasePro.getMarketDataService().getTicker(CurrencyPair.BTC_USD);

            slack.sendMessage(ticker);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
