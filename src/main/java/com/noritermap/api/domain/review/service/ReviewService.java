package com.noritermap.api.domain.review.service;

import com.noritermap.api.domain.facility.repository.FacilityRepository;
import com.noritermap.api.domain.review.entity.Review;
import com.noritermap.api.domain.review.entity.ReviewSummary;
import com.noritermap.api.domain.review.repository.ReviewRepository;
import com.noritermap.api.domain.review.repository.ReviewSummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.noritermap.api.domain.review.dto.ReviewRequestDto.*;
import static com.noritermap.api.domain.review.dto.ReviewResponseDto.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final FacilityRepository facilityRepository;
    private final ReviewSummaryRepository reviewSummaryRepository;

    @Transactional
    public Review postReview(ReviewPostRequestDto requestDto) {
        Review review = requestDto.toEntity().setFacility(facilityRepository.getReferenceById(requestDto.getFacilityId()));

        return reviewRepository.save(review);
    }

    public ReviewListResponseDto getReviewList(Long facilityId) {
        return reviewRepository.getReviewList(facilityId);
    }

    @Transactional
    public void postAISummary(ReviewSummaryPostRequestDto requestDto) {
        ReviewSummary reviewSummary = ReviewSummary.builder()
                .content(requestDto.getContent())
                .facility(facilityRepository.getReferenceById(requestDto.getFacilityId()))
                .build();

        reviewSummaryRepository.save(reviewSummary);
    }
}
