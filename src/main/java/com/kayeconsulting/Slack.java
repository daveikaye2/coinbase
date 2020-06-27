package com.kayeconsulting;

import org.knowm.xchange.dto.marketdata.Ticker;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Slack {

    private final String slackWebhookUrlStr;
    private final DateUtils dateUtils;

    public Slack() {
        slackWebhookUrlStr = System.getenv("SLACK_WEB_HOOK_MISC_POST_URL");
        dateUtils = new DateUtils();
    }

    public Response sendMessage(Ticker ticker) {
        Client client = ClientBuilder.newClient();
        WebTarget slackWebhookUrl = client.target(slackWebhookUrlStr);
        Invocation.Builder slackInvocationBuilder = slackWebhookUrl.request(MediaType.APPLICATION_JSON);
        ticker.getTimestamp();
        String message = "{'text':'"+ dateUtils.toString(ticker.getTimestamp())+": "+ticker+"'}";

        return slackInvocationBuilder.post(Entity.entity(message, MediaType.APPLICATION_JSON));
    }

}
