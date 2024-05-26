package com.noritermap.api.domain.facility.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

import static com.noritermap.api.domain.facility.dto.FacilityResponseDto.*;

public interface FacilityRepositoryCustom {
    Page<FacilitySearchResultDto> searchWithQueries(String keyword, List<String> idrodr, List<String> category, List<String> prvtPblc, String latitude, String longitude, Pageable pageable);

    FacilityInfoBaseDto getInfoBase(Long facilityId);

    FacilityInfoDetailDto getInfoDetail(Long facilityId);
    RatingAndReviewCntDto getRatingAndReviewCnt(Long facilityId);
}
