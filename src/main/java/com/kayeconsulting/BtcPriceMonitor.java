package com.kayeconsulting;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.coinbasepro.CoinbaseProExchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.math.BigDecimal;

public class BtcPriceMonitor {

    private final Slack slack;
    private final Exchange coinbasePro;

    public BtcPriceMonitor() {
        this.slack = new Slack();

        ExchangeSpecification specification = new ExchangeSpecification(CoinbaseProExchange.class);
        specification.setApiKey(System.getenv("COINBASE_API_KEY"));
        specification.setSecretKey(System.getenv("COINBASE_SECRET_KEY"));
        specification.setExchangeSpecificParametersItem("passphrase", System.getenv("COINBASE_PASSPHRASE"));
        this.coinbasePro = ExchangeFactory.INSTANCE.createExchange(specification);
    }

    public void monitor() {
        MarketDataService marketDataService = coinbasePro.getMarketDataService();
        while(true) {
            try {
                Ticker ticker = marketDataService.getTicker(CurrencyPair.BTC_USD);
                BigDecimal bid = ticker.getBid();
                if (bid.compareTo(new BigDecimal("200")) >= 0) {
                    slack.sendMessage(ticker);
                    break;
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
