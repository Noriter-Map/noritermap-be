package com.noritermap.api.domain.facility.dto;

import com.noritermap.api.domain.facility.enumTypes.FacilityEnum;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import static org.springframework.util.StringUtils.*;

public class FacilityResponseDto {

    @Schema(title = "놀이시설 검색 결과 DTO")
    @Getter
    @NoArgsConstructor
    public static class FacilitySearchResultDto {

        @Schema(description = "놀이시설 ID", example = "1")
        private Long facilityId;
        @Schema(description = "놀이시설명", example = "월곡근린공원 놀이터")
        private String pfctNm;
        @Schema(description = "도로명주소", example = "광주광역시 광산구 월곡반월로16번길 25-14")
        private String ronaAddr;
        @Schema(description = "지번주소", example = "광산구 월곡동 587")
        private String lotnoAddr;
        @Schema(description = "설치장소 (카테고리)", example = "도시공원")
        private String instlPlaceCdNm;
        @Schema(description = "민공구분", example = "민간|공공")
        private String prvtPblcYnCdNm;
        @Schema(description = "실내외구분", example = "실내|실외|undefined")
        private String idrodrCdNm;
        @Schema(description = "위도", example = "37.715133")
        private String latCrtsVl;
        @Schema(description = "경도", example = "127.269311")
        private String lotCrtsVl;
        @Schema(description = "평점", example = "4.5")
        private Double rating;
        @Schema(description = "총 리뷰 개수", example = "59")
        private Long reviewCnt;
        @Schema(description = "현재 위치로부터 거리", example = "1029.1323142")
        private Double distanceFromCur;

        @QueryProjection
        public FacilitySearchResultDto(Long facilityId, String pfctNm, String ronaAddr, String lotnoAddr, String instlPlaceCdNm, String prvtPblcYnCdNm, String idrodrCdNm, String latCrtsVl, String lotCrtsVl, Double rating, Long reviewCnt, Double distanceFromCur) {
            this.facilityId = facilityId;
            this.pfctNm = pfctNm;
            this.ronaAddr = ronaAddr;
            this.lotnoAddr = lotnoAddr;
            this.instlPlaceCdNm = instlPlaceCdNm;
            this.prvtPblcYnCdNm = prvtPblcYnCdNm;

            if (!hasText(idrodrCdNm)){
                this.idrodrCdNm = FacilityEnum.Indoor.UNDEFINED.getValue();
            }else {
                this.idrodrCdNm = idrodrCdNm;
            }
            this.latCrtsVl = latCrtsVl;
            this.lotCrtsVl = lotCrtsVl;
            this.rating = rating;
            this.reviewCnt = reviewCnt;
            this.distanceFromCur = distanceFromCur;
        }
    }

    @Schema(title = "놀이시설 상세 정보 DTO")
    @Getter
    @NoArgsConstructor
    public static class FacilityInfoDetailDto {

        @Schema(description = "놀이시설 ID", example = "1")
        private Long facilityId;
        @Schema(description = "놀이시설 일련번호", example = "42141")
        private String pfctSn;
        @Schema(description = "놀이시설명", example = "월곡근린공원 놀이터")
        private String pfctNm;
        @Schema(description = "우편번호", example = "07027")
        private String zip;
        @Schema(description = "도로명주소", example = "광주광역시 광산구 월곡반월로16번길 25-14")
        private String ronaAddr;
        @Schema(description = "지번주소", example = "광산구 월곡동 587")
        private String lotnoAddr;
        @Schema(description = "설치일자", example = "2024-03-02")
        private String instlYmd;
        @Schema(description = "설치장소 (카테고리)", example = "도시공원")
        private String instlPlaceCdNm;
        @Schema(description = "민공구분", example = "민간|공공")
        private String prvtPblcYnCdNm;
        @Schema(description = "실내외구분", example = "실내|실외")
        private String idrodrCdNm;
        @Schema(description = "위도", example = "37.715133")
        private String latCrtsVl;
        @Schema(description = "경도", example = "127.269311")
        private String lotCrtsVl;
        @Schema(description = "물놀이형 놀이시설", example = "포함|미포함")
        private String incld_water;
        @Schema(description = "CCTV 개수", example = "0|1|2|...")
        private String cctvCnt;
        @Schema(description = "보험가입 여부", example = "가입|미가입")
        private String insurance;
        @Schema(description = "안전검사 여부", example = "미검사|검사완료")
        private String safetyInsp;


        @QueryProjection
        public FacilityInfoDetailDto(Long facilityId, String pfctSn, String pfctNm, String zip, String ronaAddr, String lotnoAddr, String instlYmd, String instlPlaceCdNm, String prvtPblcYnCdNm, String idrodrCdNm, String latCrtsVl, String lotCrtsVl, String incld_water, String cctvCnt, String insurance, String safetyInsp) {
            this.facilityId = facilityId;
            this.pfctSn = pfctSn;
            this.pfctNm = pfctNm;

            if(!hasText(zip)){
                this.zip = "undefined";
            }else{
                this.zip = zip;
            }
            this.ronaAddr = ronaAddr;
            this.lotnoAddr = lotnoAddr;
            this.instlYmd = instlYmd;
            this.instlPlaceCdNm = instlPlaceCdNm;
            this.prvtPblcYnCdNm = prvtPblcYnCdNm;
            this.idrodrCdNm = idrodrCdNm;
            this.latCrtsVl = latCrtsVl;
            this.lotCrtsVl = lotCrtsVl;
            this.incld_water = incld_water;

            if (!hasText(cctvCnt)){
                this.cctvCnt = "0";
            }else{
                this.cctvCnt = cctvCnt;
            }
            this.insurance = insurance;
            this.safetyInsp = safetyInsp;
        }
    }

    @Schema(title = "놀이시설 정보 (Base) DTO")
    @Getter
    @NoArgsConstructor
    public static class FacilityInfoBaseDto {
        @Schema(description = "놀이시설 ID", example = "1")
        private Long facilityId;
        @Schema(description = "놀이시설명", example = "월곡근린공원 놀이터")
        private String pfctNm;
        @Schema(description = "우수시설 여부", example = "Y|N")
        private String exfcYn;      // 우수시설 여부
        @Schema(description = "설치장소 (카테고리)", example = "도시공원")
        private String instlPlaceCdNm;
        @Schema(description = "평점", example = "4.5")
        private Double rating;
        @Schema(description = "총 리뷰 개수", example = "59")
        private Long reviewCnt;

        @QueryProjection
        public FacilityInfoBaseDto(Long facilityId, String pfctNm, String exfcYn, String instlPlaceCdNm, Double rating, Long reviewCnt) {
            this.facilityId = facilityId;
            this.pfctNm = pfctNm;
            this.exfcYn = exfcYn;
            this.instlPlaceCdNm = instlPlaceCdNm;
            this.rating = rating;
            this.reviewCnt = reviewCnt;
        }
    }
}
