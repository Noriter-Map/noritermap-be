package com.noritermap.api.domain.review.dto;

import com.noritermap.api.domain.review.entity.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewRequestDto {

    @Schema(title = "리뷰 등록 요청 DTO")
    @Getter
    @NoArgsConstructor
    public static class ReviewPostRequestDto {

        @Schema(description = "놀이시설 ID", example = "1")
        Long facilityId;
        @Schema(description = "작성자 닉네임", example = "흥민맘")
        String nickname;
        @Schema(description = "리뷰 내용", example = "시설이 청결해요.")
        String content;
        @Schema(description = "별점", example = "3.0")
        Double rating;

        public Review toEntity(){
            return Review.builder()
                    .nickname(nickname)
                    .content(content)
                    .rating(rating)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ReviewSummaryPostRequestDto {
        Long facilityId;
        String content;
    }
}
