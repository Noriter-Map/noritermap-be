package com.noritermap.api.global.slack;

import com.noritermap.api.domain.facility.dto.FacilityResponseDto;
import com.noritermap.api.domain.review.entity.Review;
import com.noritermap.api.mq.MessageDto;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class SlackAspect {

    @Value("${rabbitmq.queue.name}")
    private String QUEUE_NAME;

    @Value("${rabbitmq.exchange.name}")
    private String EXCHANGE_NAME;

    @Value("${rabbitmq.routing.key}")
    private String ROUTING_KEY;

    private final RabbitTemplate rabbitTemplate;

    // 리뷰 등록
    @AfterReturning(
            pointcut = "execution(* com.noritermap.api.domain.review.service.ReviewService.postReview(com.noritermap.api.domain.review.dto.ReviewRequestDto.ReviewPostRequestDto))",
            returning = "review"
    )
    @Async
    public void afterRegisterReview(Review review){
        String message = String.format(
                "facility_id : %s\n" +
                        "시설 이름 : %s\n" +
                        "닉네임 : %s\n" +
                        "별점 : %s\n" +
                        "내용 : %s\n"
                , review.getFacility().getId()+"", review.getFacility().getPfctNm(), review.getNickname(), review.getRating()+"", review.getContent());

        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, new MessageDto("#알림-리뷰중대", message));
    }

    // 시설 상세정도 조회
    @AfterReturning(
            pointcut = "execution(* com.noritermap.api.domain.facility.service.FacilityService.getFacilityInfoBase(Long))",
            returning = "result"
    )
    public void afterGetFacilityInfoBase(FacilityResponseDto.FacilityInfoBaseDto result){
        String message = String.format("facility id : %s, 시설 이름 : %s", result.getFacilityId(), result.getPfctNm());

        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, new MessageDto("#알림-상세정보조회", message));
    }
}