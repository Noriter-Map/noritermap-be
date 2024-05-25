package com.noritermap.api.domain.review.repository;

import static com.noritermap.api.domain.review.dto.ReviewResponseDto.*;

public interface ReviewRepositoryCustom {
    ReviewListResponseDto getReviewList(Long facilityId);
}
