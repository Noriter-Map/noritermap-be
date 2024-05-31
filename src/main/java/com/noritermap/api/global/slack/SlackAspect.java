package com.noritermap.api.global.slack;

import com.noritermap.api.domain.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class SlackAspect {
    private final SlackUtil slackUtil;

    // 리뷰 등록
    @AfterReturning(
            pointcut = "execution(* com.noritermap.api.domain.review.service.ReviewService.postReview(com.noritermap.api.domain.review.dto.ReviewRequestDto.ReviewPostRequestDto))",
            returning = "review"
    )
    public void afterRegisterReview(Review review){
        slackUtil.sendReviewRegisteredSlackMessage(review.getFacility().getId(), review.getFacility().getPfctNm(), review.getNickname(), review.getRating(), review.getContent());
    }

}