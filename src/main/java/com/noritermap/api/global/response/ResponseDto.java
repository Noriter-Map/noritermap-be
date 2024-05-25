package com.noritermap.api.global.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ResponseDto<T> {
    private final Integer status;     // 1. 성공, -1 실패
    private final T data;
}