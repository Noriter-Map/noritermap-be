package com.noritermap.api.domain.review.repository;

import com.noritermap.api.domain.review.entity.ReviewSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewSummaryRepository extends JpaRepository<ReviewSummary, Long> {
}
