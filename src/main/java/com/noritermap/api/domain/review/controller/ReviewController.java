package com.noritermap.api.domain.review.controller;

import com.noritermap.api.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.noritermap.api.domain.review.dto.ReviewRequestDto.*;

@Tag(name = "리뷰 등록 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/gj/v1/review")
public class ReviewController {
    private final ReviewService reviewService;

    @Operation(summary = "리뷰 등록하기", description = "응답 데이터는 없습니다.")
    @PostMapping("/facility")
    public ResponseEntity<?> postReview(@RequestBody ReviewPostRequestDto requestDto){
        reviewService.postReview(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "AI 리뷰 요약 내용 등록", description = "프론트엔드에서 사용할 일은 없습니다.")
    @PostMapping("/facility/{id}/ai-review/only-for-batch")
    public ResponseEntity<?> postAISummary(@RequestBody ReviewSummaryPostRequestDto requestDto){
        reviewService.postAISummary(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
