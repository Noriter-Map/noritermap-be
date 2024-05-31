package com.noritermap.api.global.slack;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class SlackUtil {

    @Value("${secret-key.slack}")
    private String SLACK_TOKEN;

    public void sendSlackMessage(String message, String channel){
        try{
            MethodsClient methods = Slack.getInstance().methods(SLACK_TOKEN);

            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel(channel)
                    .text(message)
                    .build();

            methods.chatPostMessage(request);
        }catch (SlackApiException | IOException e) {
            log.error(e.getMessage());
        }
    }
}
