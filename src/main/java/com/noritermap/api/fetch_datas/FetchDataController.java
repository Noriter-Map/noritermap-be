package com.noritermap.api.fetch_datas;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fetch-data")
public class FetchDataController {
    private final FetchDataService fetchDataService;

//    @GetMapping("/facilities")
//    public ResponseEntity<?> fetchFacilitiesDatas(){
//        fetchDataService.fetchFacilitiesData();
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//    @GetMapping("/rides")
//    public ResponseEntity<?> fetchRidesDatas(){
//        fetchDataService.fetchRidesData();
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
