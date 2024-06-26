package com.noritermap.api.domain.facility.service;

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

    public Page<FacilitySearchResultDto> search(String keyword, List<String> idrodr, List<String> category, List<String> prvtPblc, String latitude, String longitude, Pageable pageable) {
        return facilityRepository.searchWithQueries(keyword, idrodr, category, prvtPblc, latitude, longitude, pageable);
    }

    public FacilityInfoBaseDto getFacilityInfoBase(Long facilityId) {
        return facilityRepository.getInfoBase(facilityId);
    }

    public FacilityInfoDetailDto getFacilityInfoDetail(Long facilityId) {
        return facilityRepository.getInfoDetail(facilityId);
    }

    public RatingAndReviewCntDto getRatingAndReviewCnt(Long facilityId) {
        return facilityRepository.getRatingAndReviewCnt(facilityId);
    }
}
