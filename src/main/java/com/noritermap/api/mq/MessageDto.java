package com.noritermap.api.mq;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageDto {
    private String channel;
    private String message;
}