package com.kayeconsulting;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.coinbasepro.CoinbaseProExchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class BtcPriceMonitor {
    public void monitor() {
        Exchange coinbasePro = ExchangeFactory.INSTANCE.createExchange(CoinbaseProExchange.class);
        try {
            Ticker ticker = coinbasePro.getMarketDataService().getTicker(CurrencyPair.BTC_USD);
            System.out.println(ticker);

            String slackWebhookUrlStr = System.getenv("SLACK_WEB_HOOK_MISC_POST_URL");

            //Slack notification
            Client client = ClientBuilder.newClient();
            WebTarget slackWebhookUrl = client.target(slackWebhookUrlStr);
            Builder slackInvocationBuilder = slackWebhookUrl.request(MediaType.APPLICATION_JSON);
            String message = "{'text':'"+ ticker +"'}";
            Response post = slackInvocationBuilder.post(Entity.entity(message, MediaType.APPLICATION_JSON));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
