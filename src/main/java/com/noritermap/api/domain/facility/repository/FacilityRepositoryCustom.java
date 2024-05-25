package com.noritermap.api.domain.facility.repository;

import com.noritermap.api.domain.facility.enumTypes.FacilityEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

import static com.noritermap.api.domain.facility.dto.FacilityResponseDto.*;

public interface FacilityRepositoryCustom {
    Page<FacilitySearchResultDto> searchWithQueries(String keyword, List<Boolean> isIndoor, List<FacilityEnum.Category> category, List<FacilityEnum.prvtPblc> prvtPblc, String latitude, String longitude, Pageable pageable);

    FacilityInfoBaseDto getInfoBase(Long facilityId);

    FacilityInfoDetailDto getInfoDetail(Long facilityId);
}
