package com.noritermap.api.domain.review.repository;

import com.noritermap.api.domain.review.dto.QReviewResponseDto_ReviewDetailDto;
import com.noritermap.api.domain.review.dto.QReviewResponseDto_ReviewListResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

import static com.noritermap.api.domain.review.dto.ReviewResponseDto.*;
import static com.noritermap.api.domain.review.entity.QReview.review;
import static com.noritermap.api.domain.review.entity.QReviewSummary.*;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public ReviewListResponseDto getReviewList(Long facilityId) {
        List<ReviewDetailDto> reviewDetailDtoList = queryFactory
                .select(new QReviewResponseDto_ReviewDetailDto(review.nickname, review.content, review.rating))
                .from(review)
                .where(review.facility.id.eq(facilityId))
                .fetch();

        ReviewListResponseDto responseDto = queryFactory
                .select(new QReviewResponseDto_ReviewListResponseDto(reviewSummary.content, review.rating.avg(), review.count()))
                .from(review)
                .leftJoin(reviewSummary)
                .on(reviewSummary.facility.id.eq(facilityId))
                .where(review.facility.id.eq(facilityId))
                .groupBy(review.facility)
                .fetchOne();

        Objects.requireNonNull(responseDto).setReviews(reviewDetailDtoList);

        return responseDto;

    }
}
