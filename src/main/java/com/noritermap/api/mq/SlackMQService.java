package com.noritermap.api.mq;

import com.noritermap.api.global.slack.SlackUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


/**
 * 메시지를 publish 할 때는 RabbitTemplate 의 ConvertAndSend 메소드를 사용하고
 * 메시지를 구독할 때는 @RabbitListener 을 사용
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SlackMQService {

    private final SlackUtil slackUtil;

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(MessageDto messageDto) {
        slackUtil.sendSlackMessage(messageDto.getMessage(), messageDto.getChannel());
    }

}
