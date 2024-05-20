package com.noritermap.api.fetch_datas;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@Component
public class FetchDataUtil {
    private static final String FACILITIES_URL = "https://apis.data.go.kr/1741000/pfc2/pfc/getPfctInfo2";
    private static final String RIDES_URL = "https://apis.data.go.kr/1741000/ride3/getRide3";

    @Value("${secret-key.gdata}")
    private String AUTH_KEY;

    private WebClient webClient = WebClient.builder().build();

    public JSONObject requestGetFacilities(int pageIndex, int recordPerPage){
        String uriString = String.format("%s?serviceKey=%s&pageIndex=%d&recordCountPerPage=%d",
                FACILITIES_URL, AUTH_KEY, pageIndex, recordPerPage);

        URI uri;
        try {
            uri = new URI(uriString);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to build URI", e);
        }

        // 출력 URI 확인
        System.out.println("Generated URI: " + uri.toString());

        String responseString = webClient.get()
                .uri(uri)
                .header("Accept", "application/json, text/plain, */*")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        if (Objects.requireNonNull(responseString).isEmpty()) {
            throw new RuntimeException("공공 데이터 서버와 연결에 실패하였습니다.");
        }

        return parseResponse(responseString);
    }

    public JSONObject requestGetRides(int pageIndex, int recordPerPage){
        String uriString = String.format("%s?serviceKey=%s&pageIndex=%d&recordCountPerPage=%d",
                RIDES_URL, AUTH_KEY, pageIndex, recordPerPage);

        URI uri;
        try {
            uri = new URI(uriString);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to build URI", e);
        }

        // 출력 URI 확인
        System.out.println("Generated URI: " + uri.toString());

        String responseString = webClient.get()
                .uri(uri)
                .header("Accept", "application/json, text/plain, */*")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        if (Objects.requireNonNull(responseString).isEmpty()) {
            throw new RuntimeException("공공 데이터 서버와 연결에 실패하였습니다.");
        }

        return parseResponse(responseString);
    }



    private JSONObject parseResponse(String result){
        try {
            JSONParser parser = new JSONParser();

            return (JSONObject) parser.parse(result);
        }catch (ParseException e){
            throw new RuntimeException("JSON 객체로 파싱에 실패하였습니다.");
        }
    }
}
