package com.noritermap.api.fetch_datas;

import com.noritermap.api.domain.facility.entity.Facility;
import com.noritermap.api.domain.facility.repository.FacilityRepository;
import com.noritermap.api.domain.rides.dto.RidesDto;
import com.noritermap.api.domain.rides.repository.RidesRepository;
import com.noritermap.api.domain.facility.dto.FacilityDto;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            List<FacilityDto.FacilityResponseDto> facilityResponseDtoList = new ArrayList<>();
            for (Object itemObj : itemsArray) {
                JSONObject item = (JSONObject) itemObj;
                FacilityDto.FacilityResponseDto dto = FacilityDto.FacilityResponseDto.fromJsonObject(item);
                facilityResponseDtoList.add(dto);
            }

            // insert to database
            facilityResponseDtoList.stream()
                    .map(FacilityDto.FacilityResponseDto::toEntity)
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
            List<RidesDto.RidesResponseDto> RideResponseDtoList = new ArrayList<>();
            for (Object itemObj : itemsArray) {
                JSONObject item = (JSONObject) itemObj;
                RidesDto.RidesResponseDto dto = RidesDto.RidesResponseDto.fromJsonObject(item);
                RideResponseDtoList.add(dto);
            }

            // insert to database
            RideResponseDtoList.stream()
                    .map(RidesDto.RidesResponseDto::toEntity)
                    .forEach(ridesRepository::save);

        }
    }

//    @Transactional
//    public void readFacilitiesFromCsv() {
//        try {
//            ClassPathResource resource = new ClassPathResource("static/광주광역시_어린이_놀이시설_현황_20240216.csv");
//            Reader reader = new FileReader(resource.getFile());
//            CSVReader csvReader = new CSVReader(reader);
//            String[] line;
//            boolean isFirstLine = true;
//
//            while ((line = csvReader.readNext()) != null) {
//                if (isFirstLine) {
//                    isFirstLine = false; // skip header
//                    continue;
//                }
//
//                String pfctSn = line[0];
//                String idrodrCdNm = line[6];
//
//                Optional<Facility> facilityOptional = facilityRepository.findByPfctSn(pfctSn);
//
//                facilityOptional.ifPresent(facility -> facility.setIdrodrCdNm(idrodrCdNm));
//
//                if (facilityOptional.isEmpty()){
//                    Facility facility = Facility.builder()
//                            .pfctSn(line[0])
//                            .pfctNm(line[1])
//                            .ronaAddr(line[2])
//                            .instlYmd(line[3])
//                            .instlPlaceCdNm(line[4])
//                            .prvtPblcYnCdNm(line[5])
//                            .idrodrCdNm(line[6])
//                            .incld_water(line[7])
//                            .build();
//
//                    facilityRepository.save(facility);
//                }
//
//            }
//
//            csvReader.close();
//        } catch (IOException | CsvValidationException e) {
//            e.printStackTrace();
//        }
//    }

    @Transactional
    public void fetchGwangjuFacilitiesData() {
        for (int i = 1; i <= 21; i++){
            JSONObject json = fetchDataUtil.requestGetGwangJuFacilities(i, 200);
            JSONArray datasArray = (JSONArray) json.get("data");

            // Convert items to DTO objects
            List<FacilityDto.FacilityResponseDto> facilityResponseDtoList = new ArrayList<>();
            for (Object itemObj : datasArray) {
                JSONObject item = (JSONObject) itemObj;
                FacilityDto.FacilityResponseDto dto = FacilityDto.FacilityResponseDto.builder()
                        .pfctNm(item.get("놀이시설명").toString())
                        .lotnoAddr(item.get("지번 주소").toString())
                        .ronaAddr(item.get("도로명 주소").toString())
                        .prvtPblcYnCdNm(item.get("민공구분").toString())
                        .instlPlaceCdNm(item.get("설치장소").toString())
                        .safetyInsp(item.get("안전검사여부").toString())
                        .incld_water(item.get("물놀이형놀이시설").toString())
                        .zip(item.get("우편번호").toString())
                        .cctvCnt(item.get("CCTV").toString())
                        .insurance(item.get("보험가입여부").toString())
                        .instlYmd(item.get("설치일자").toString())
                        .pfctSn(item.get("시설번호").toString())
                        // csv 파일에서 실내외 추가해야 함
                        .build();
                facilityResponseDtoList.add(dto);

            }

            // insert to database
            facilityResponseDtoList.stream()
                    .map(FacilityDto.FacilityResponseDto::toEntity)
                    .forEach(facilityRepository::save);

        }
    }
}
