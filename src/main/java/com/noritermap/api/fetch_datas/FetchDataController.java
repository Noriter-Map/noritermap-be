package com.noritermap.api.fetch_datas;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "서버 내부 데이터 처리용 - 무시하세요", description = "")
@RestController
@RequiredArgsConstructor
@RequestMapping("/fetch-data")
public class FetchDataController {
    private final FetchDataService fetchDataService;

//    @GetMapping("/facilities")
//    public ResponseEntity<?> fetchFacilitiesData(){
//        fetchDataService.fetchFacilitiesData();
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//    @GetMapping("/rides")
//    public ResponseEntity<?> fetchRidesData(){
//        fetchDataService.fetchRidesData();
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//    @GetMapping("/facilities/gwangju")
//    public ResponseEntity<?> fetchGwangjuFacilitiesData(){
//        fetchDataService.fetchGwangjuFacilitiesData();
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @PutMapping("/facilities/gwangju/fill")
//    public ResponseEntity<?> fillGwangjuFacilitiesData(){
//        fetchDataService.readFacilitiesFromCsv();
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//    @GetMapping("/latlot")
//    public ResponseEntity<?> fetchLatlot(
//            @RequestParam("pfct_sn") String pfctSn,
//            @RequestParam("lat") String lat,
//            @RequestParam("lot") String lot
//    ){
//        fetchDataService.fetchLatlot(pfctSn, lat, lot);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
