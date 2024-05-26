package com.noritermap.api.domain.facility.controller;

import com.noritermap.api.domain.facility.service.FacilityService;
import com.noritermap.api.domain.review.service.ReviewService;
import com.noritermap.api.global.response.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.noritermap.api.domain.facility.dto.FacilityResponseDto.*;
import static com.noritermap.api.domain.review.dto.ReviewResponseDto.*;

@Tag(name = "놀이시설 API", description = "놀이시설 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/gj/v1/facility")
public class FacilityController {
    private final FacilityService facilityService;
    private final ReviewService reviewService;

    @Operation(summary = "놀이시설 검색하기",
            description = "쿼리를 붙이면 검색 조건에 추가. 쿼리를 붙이지 않으면 ALL 체크 한 것으로 간주한다.\n\n" +
                    "페이지 관련 파라미터는 필수이다. sort 는 안 보내도 된다.\n\n" +
                    "------------------------------------\n\n" +
                    "if 처음 사이드바 열었을 때 시설 리스트:\n\n" +
                    "----if 사용자 현재 위치를 알 떄 : 위도 경도만 담아서 검색 (물론 페이지값도)\n\n" +
                    "----else if 사용자 현재 위치를 모를 때 : 페이지 파라미터만 담아서 보내면 디폴트로 광주광역시청 좌표 기준\n\n" +
                    "--------------------------------------------------------------------------------\n\n" +
                    "응답 데이터 중 distanceFromCur 는 현재 위치에서 시설까지의 거리입니다. \n\n" +
                    "단위는 m(미터) 이며, 예를 들어 distanceFromCur: 1129.123412 일 경우 1.1km(29m 버림) 입니다.\n\n" +
                    "쿼리를 바로 쓰느라 변환하지 못했음. 프론트에서 단위 변환 바람")
    @GetMapping("/search")
    public ResponseEntity<ResponseDto<Page<FacilitySearchResultDto>>> searchFacilities(
            @Parameter(name = "keyword", description = "검색어", example = "근린")
            @RequestParam(value = "keyword", required = false) String keyword,

            @Parameter(name = "idrodr", in = ParameterIn.QUERY,
                    description = "실내외, {indoor : 실내, outdoor : 실외}\n\n 실내외 값이 없는 데이터가 간혹 있다. 나중에 응답 값 받을 떄 참고할 것.\n\n 둘 다면 indoor,outdoor 로 보내면 됨",
                    schema = @Schema(type="string", example = "indoor,outdoor"))
            @RequestParam(value = "idrodr", required = false) List<String> idrodr,

            @Parameter(name = "category", description = "설치장소\n\n " +
                    "{C1: 주택단지, C2: 도시공원, C3: 어린이집, C4: 놀이제공영업소, C5: 식품접객업소, C6: 주상복합, C7: 아동복지시설, C8: 종교시설, C9: 대규모점포, C10: 육아종합지원센터, C11: 박물관, C12: 야영장, C13: 기타}",
                    schema = @Schema(type="string", example = "C1,C3,C7"))
            @RequestParam(value = "category", required = false) List<String> category,

            @Parameter(name = "prvt_pblc", description = "민공구분", schema = @Schema(type="string", example = "public,private"))
            @RequestParam(value = "prvt_pblc", required = false) List<String> prvtPblc,

            @Parameter(name = "curLatitude", description = "현재 위도", example = "37.234312")
            @RequestParam(value = "curLatitude", required = false) String latitude,

            @Parameter(name = "curLongitude", description = "현재 경도", example = "127.123414")
            @RequestParam(value = "curLongitude", required = false) String longitude,
            Pageable pageable
    ){
        Page<FacilitySearchResultDto> results = facilityService.search(keyword, idrodr, category, prvtPblc, latitude, longitude, pageable);

        return new ResponseEntity<>(new ResponseDto<>(1, results), HttpStatus.OK);
    }

    @Operation(summary = "시설 1개 정보: Top 부분", description = "페이징 아닙니다. 모든 데이터를 한 번에 드립니다.")
    @GetMapping("/info/base/{id}")
    public ResponseEntity<ResponseDto<FacilityInfoBaseDto>> getFacilityInfoBase(
            @Schema(description = "facility id", example = "1")
            @PathVariable(value = "id") Long facilityId
    ){
        FacilityInfoBaseDto results = facilityService.getFacilityInfoBase(facilityId);

        return new ResponseEntity<>(new ResponseDto<>(1, results), HttpStatus.OK);
    }

    @Operation(summary = "시설 1개 정보: Bottom-left 부분")
    @GetMapping("/info/detail/{id}")
    public ResponseEntity<ResponseDto<FacilityInfoDetailDto>> getFacilityInfoDetail(
            @Schema(description = "facility id", example = "1")
            @PathVariable(value = "id") Long facilityId
    ){
        FacilityInfoDetailDto result = facilityService.getFacilityInfoDetail(facilityId);

        return new ResponseEntity<>(new ResponseDto<>(1, result), HttpStatus.OK);
    }

    @Operation(summary = "시설 1개 정보: Bottom-right 부분")
    @GetMapping("/info/review/{id}")
    public ResponseEntity<ResponseDto<ReviewListResponseDto>> getFacilityInfoReview(
            @Schema(description = "facility id", example = "1")
            @PathVariable(value = "id") Long facilityId
    ){
        ReviewListResponseDto results = reviewService.getReviewList(facilityId);

        return new ResponseEntity<>(new ResponseDto<>(1, results), HttpStatus.OK);
    }
}
