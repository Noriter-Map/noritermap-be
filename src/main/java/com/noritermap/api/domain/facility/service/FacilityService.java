package com.noritermap.api.domain.facility.service;

import com.noritermap.api.domain.facility.enumTypes.FacilityEnum;
import com.noritermap.api.domain.facility.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.noritermap.api.domain.facility.dto.FacilityResponseDto.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FacilityService {
    private final FacilityRepository facilityRepository;

    public Page<FacilitySearchResultDto> search(String keyword, List<Boolean> isIndoor, List<FacilityEnum.Category> category, List<FacilityEnum.prvtPblc> prvtPblc, String latitude, String longitude, Pageable pageable) {
        return facilityRepository.searchWithQueries(keyword, isIndoor, category, prvtPblc, latitude, longitude, pageable);
    }

    public FacilityInfoBaseDto getFacilityInfoBase(Long facilityId) {
        return facilityRepository.getInfoBase(facilityId);
    }

    public FacilityInfoDetailDto getFacilityInfoDetail(Long facilityId) {
        return facilityRepository.getInfoDetail(facilityId);
    }
}
