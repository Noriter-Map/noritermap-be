package com.noritermap.api.fetch_datas;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("/extract-latlot-data")
//    public ResponseEntity<?> extractData(){
//        fetchDataService.extractData();
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @GetMapping("/modify-latlot")
    public ResponseEntity<?> modifyLatlot(@RequestParam(name = "index") Integer index){
        fetchDataService.modifyLatlot(index);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/add-latlot")
    public ResponseEntity<?> addLatlot(@RequestParam(name = "index") Integer index){
        fetchDataService.addLatlot(index);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/extract-marker-data")
    public ResponseEntity<?> extractMarkerData(){
        fetchDataService.extractMarkerData();

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PostMapping("/extract-rides-from-nationwide-to-gj")
//    public ResponseEntity<?> extractOnlyGjRides(){
//        fetchDataService.extractOnlyGjRides();
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
