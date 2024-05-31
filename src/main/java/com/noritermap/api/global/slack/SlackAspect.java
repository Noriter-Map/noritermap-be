package com.noritermap.api.global.slack;

import com.noritermap.api.domain.facility.dto.FacilityResponseDto;
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
        String message = String.format(
                "facility_id : %s\n" +
                        "시설 이름 : %s\n" +
                        "닉네임 : %s\n" +
                        "별점 : %s\n" +
                        "내용 : %s\n"
                , review.getFacility().getId()+"", review.getFacility().getPfctNm(), review.getNickname(), review.getRating()+"", review.getContent());

        slackUtil.sendSlackMessage(message, "#알림-리뷰중대");
    }

    // 시설 상세정도 조회
    @AfterReturning(
            pointcut = "execution(* com.noritermap.api.domain.facility.service.FacilityService.getFacilityInfoBase(Long))",
            returning = "result"
    )
    public void afterGetFacilityInfoBase(FacilityResponseDto.FacilityInfoBaseDto result){
        String message = String.format("facility id : %s, 시설 이름 : %s", result.getFacilityId(), result.getPfctNm());

        slackUtil.sendSlackMessage(message, "#알림-상세정보조회");
    }

}