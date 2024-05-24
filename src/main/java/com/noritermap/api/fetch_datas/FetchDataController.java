package com.noritermap.api.fetch_datas;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
