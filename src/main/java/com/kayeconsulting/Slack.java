package com.kayeconsulting;

import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import org.knowm.xchange.dto.marketdata.Ticker;

public class Slack {

    private final String token;
    private final DateUtils dateUtils;

    public Slack() {
        token = System.getenv("SLACK_COINBASE_BOT_TOKEN");
        dateUtils = new DateUtils();
    }

    public ChatPostMessageResponse sendMessage(Ticker ticker) {
        try {
            com.slack.api.Slack slack = com.slack.api.Slack.getInstance();
            MethodsClient methods = slack.methods(token);
            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel("U015U0UCQQN")
                    .text("<@U015U0UCQQN|David> "+ticker.toString())
                    .build();

            return methods.chatPostMessage(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
