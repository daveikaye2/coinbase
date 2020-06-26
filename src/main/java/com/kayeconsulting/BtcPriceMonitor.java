package com.kayeconsulting;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BtcPriceMonitor {
    public void monitor() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("https://api.pro.coinbase.com");
        WebTarget tickerWebTarget = webTarget.path("products/BTC-USD/ticker");
        Builder invocationBuilder = tickerWebTarget.request(MediaType.APPLICATION_JSON);
        Ticker ticker = invocationBuilder.get(Ticker.class);
        System.out.println(ticker);

        String slackWebhookUrlStr = System.getenv("SLACK_WEB_HOOK_MISC_POST_URL");
        WebTarget slackWebhookUrl = client.target(slackWebhookUrlStr);
        Builder slackInvocationBuilder = slackWebhookUrl.request(MediaType.APPLICATION_JSON);
        String message = "{'text':'"+ticker+"'}";
        Response post = slackInvocationBuilder.post(Entity.entity(message, MediaType.APPLICATION_JSON));
        System.out.println(post);
    }
}
