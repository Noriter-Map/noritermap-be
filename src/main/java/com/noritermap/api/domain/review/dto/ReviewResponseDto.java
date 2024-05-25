package com.noritermap.api.domain.review.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

public class ReviewResponseDto {

    @Schema(title = "리뷰 1개 상세 정보 DTO")
    @Getter
    @Builder
    @NoArgsConstructor
    public static class ReviewDetailDto {
        @Schema(description = "작성자 닉네임", example = "흥민맘")
        String nickname;
        @Schema(description = "리뷰 내용", example = "시설이 청결해요.")
        String content;
        @Schema(description = "별점 (정수형이 아닌 실수형입니다)", example = "4.0")
        Double rating;

        @QueryProjection
        public ReviewDetailDto(String nickname, String content, Double rating) {
            this.nickname = nickname;
            this.content = content;
            this.rating = rating;
        }
    }

    @Schema(title = "놀이시설에 대한 리뷰 리스트 DTO")
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewListResponseDto {

        @Schema(description = "평균 별점", example = "4.43")
        Double ratingAvg;
        @Schema(description = "총 리뷰 개수", example = "54")
        Long reviewCnt;
        @Schema(description = "AI 리뷰 요약", example = "그냥 문자열 (최대 500자)")
        String aiSummary;

        @Setter
        List<ReviewDetailDto> reviews;

        @QueryProjection
        public ReviewListResponseDto(String aiSummary, Double ratingAvg, Long reviewCnt) {
            this.aiSummary = aiSummary;
            this.ratingAvg = ratingAvg;
            this.reviewCnt = reviewCnt;
        }


    }
}
