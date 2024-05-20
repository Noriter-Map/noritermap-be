package com.noritermap.api.fetch_datas;

import com.noritermap.api.domain.facility.repository.FacilityRepository;
import com.noritermap.api.domain.rides.dto.RidesDto;
import com.noritermap.api.domain.rides.repository.RidesRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.noritermap.api.domain.facility.dto.FacilityDto.*;
import static com.noritermap.api.domain.rides.dto.RidesDto.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FetchDataService {
    private final FacilityRepository facilityRepository;
    private final RidesRepository ridesRepository;
    private final FetchDataUtil fetchDataUtil;

    @Transactional
    public void fetchFacilitiesData(){
        for (int i = 1; i <= 422; i++){
            JSONObject json = fetchDataUtil.requestGetFacilities(i, 200);
            JSONObject responseJson = (JSONObject) json.get("response");
            JSONObject bodyJson = (JSONObject) responseJson.get("body");
            JSONArray itemsArray = (JSONArray) bodyJson.get("items");

            // Convert items to DTO objects
            List<FacilityResponseDto> facilityResponseDtoList = new ArrayList<>();
            for (Object itemObj : itemsArray) {
                JSONObject item = (JSONObject) itemObj;
                FacilityResponseDto dto = FacilityResponseDto.fromJsonObject(item);
                facilityResponseDtoList.add(dto);
            }

            // insert to database
            facilityResponseDtoList.stream()
                    .map(FacilityResponseDto::toEntity)
                    .forEach(facilityRepository::save);

        }
    }

    @Transactional
    public void fetchRidesData() {
        for (int i = 1; i <= 841; i++){
            JSONObject json = fetchDataUtil.requestGetRides(i, 500);
            JSONObject responseJson = (JSONObject) json.get("response");
            JSONObject bodyJson = (JSONObject) responseJson.get("body");
            JSONArray itemsArray = (JSONArray) bodyJson.get("items");

            // Convert items to DTO objects
            List<RidesResponseDto> RideResponseDtoList = new ArrayList<>();
            for (Object itemObj : itemsArray) {
                JSONObject item = (JSONObject) itemObj;
                RidesResponseDto dto = RidesResponseDto.fromJsonObject(item);
                RideResponseDtoList.add(dto);
            }

            // insert to database
            RideResponseDtoList.stream()
                    .map(RidesResponseDto::toEntity)
                    .forEach(ridesRepository::save);

        }
    }
}
