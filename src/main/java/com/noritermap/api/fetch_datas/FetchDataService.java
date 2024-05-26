package com.noritermap.api.fetch_datas;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Transactional
    public void fetchLatlot(String pfctSn, String lat, String lot) {
        Optional<Facility> facilityOptional = facilityRepository.findByPfctSn(pfctSn);

        if (facilityOptional.isPresent()){
            Facility facilityPS = facilityOptional.get();

            facilityPS.setLatCrtsVl(lat);
            facilityPS.setLotCrtsVl(lot);

            System.out.println(pfctSn);
        }


    }


    public void extractData() {
        List<Facility> all = facilityRepository.findAll();
        ObjectMapper objectMapper = new ObjectMapper();

        // static 폴더 경로를 지정합니다.
        String directoryPath = "src/main/resources/static";
        File directory = new File(directoryPath);

        for (int i = 0; i < all.size(); i += 100) {
            // 범위를 벗어나지 않도록 서브리스트를 생성합니다.
            int end = Math.min(i + 100, all.size());
            System.out.print(i);
            List<Facility> tmpList = all.subList(i, end);
            List<FacilityLatLonDto> collect = tmpList.stream().map(FacilityLatLonDto::from).toList();

            // JSON 파일에 저장할 데이터를 배열 형태로 변환합니다.
            List<Object[]> dataToWrite = new ArrayList<>();
            for (FacilityLatLonDto dto : collect) {
                Object[] dataArray = {
                        dto.getFacilityId(),
                        dto.getLatitude(),
                        dto.getLongitude(),
                        dto.getFacilityName(),
                        dto.getAddr()
                };
                dataToWrite.add(dataArray);
            }

            // 파일명을 지정합니다.
            String fileName = directoryPath + "/facility_data_" + (i / 100 + 1) + ".json";
            File file = new File(fileName);

            // 데이터를 JSON 파일로 저장합니다.
            try {
                objectMapper.writeValue(file, dataToWrite);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Transactional
    public void modifyLatlot(Integer index) {
        Resource facilityFile = new ClassPathResource("static/todo/TO_MODIFY_FCT_" + index + ".txt");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(facilityFile.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                if (parts.length == 3) {
                    Long facility_id = Long.parseLong(parts[0].trim());
                    String latCrtsVl = parts[1].trim();
                    String lotCrtsVl = parts[2].trim();

                    // 여기에 원하는 로직을 추가하세요
                    Facility facility = facilityRepository.findById(facility_id)
                            .orElseThrow(() -> new RuntimeException(facility_id + " 가 없음."));

                    facility.setLatCrtsVl(latCrtsVl);
                    facility.setLotCrtsVl(lotCrtsVl);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void addLatlot(Integer index) {
        Resource facilityFile = new ClassPathResource("static/todo/TO_ADD_FCT_" + index + ".txt");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(facilityFile.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                if (parts.length == 3) {
                    Long facility_id = Long.parseLong(parts[0].trim());
                    String latCrtsVl = parts[1].trim();
                    String lotCrtsVl = parts[2].trim();

                    // 여기에 원하는 로직을 추가하세요
                    Facility facility = facilityRepository.findById(facility_id)
                            .orElseThrow(() -> new RuntimeException(facility_id + " 가 없음."));

                    facility.setLatCrtsVl(latCrtsVl);
                    facility.setLotCrtsVl(lotCrtsVl);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void extractMarkerData() {
        List<Facility> facilityList = facilityRepository.findAll();
        JSONObject rootObject = new JSONObject();
        JSONArray dataArray = new JSONArray();

        for (Facility facility : facilityList) {
            // --> 아직 id 500까지만
            if (facility.getId() > 500) break;

            JSONObject facilityObject = new JSONObject();
            facilityObject.put("facility_id", facility.getId().toString());
            facilityObject.put("lat", facility.getLatCrtsVl());
            facilityObject.put("lot", facility.getLotCrtsVl());
            facilityObject.put("pfct_nm", facility.getPfctNm());

            if (Objects.isNull(facility.getRonaAddr()) || !StringUtils.hasText(facility.getRonaAddr())){
                facilityObject.put("addr", facility.getLotnoAddr());
            }else {
                facilityObject.put("addr", facility.getRonaAddr());
            }
            facilityObject.put("instl_place_cd_nm", facility.getInstlPlaceCdNm());
            facilityObject.put("zip", facility.getZip());

            dataArray.add(facilityObject);
        }

        rootObject.put("data", dataArray);

        try {
            File directory = new File("src/main/resources/static/marker_data");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File file = new File(directory, "marker_data.json");
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(rootObject.toJSONString());
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
