package com.noritermap.api.domain.facility.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Facility {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FACILITY_ID")
    private Long id;

    private String pfctSn;              // 놀이시설 일련번호
    private String pfctNm;              // 놀이시설명
    private String zip;                 // 우편번호
    private String lotnoAddr;           // 지번주소
    private String lotnoDaddr;          // 지번상세주소
    private String ronaAddr;            // 도로명주소
    private String ronaDaddr;           // 도로명 상세 주소
    private String instlYmd;            // 설치일자
    private String clsgYmd;             // 폐쇄일자
    private String acptnYmd;            // 인수일자
    private String etcSufa;             // 부대시설 (기타)
    private String exfcYn;              // 우수시설여부
    private String fcar;                // 시설면적
    private String instlPlaceCd;        // 설치장소코드
    private String instlPlaceCdNm;      // 설치장소코드명
    private String dutyCd;              // 의무시설여부코드
    private String dutyCdNm;            // 의무시설여부코드명
    private String prvtPblcYnCd;        // 민간공공구분코드
    private String prvtPblcYnCdNm;      // 민간공공구분코드명
    private String operYnCd;            // 시설운영구분코드
    private String operYnCdNm;          // 시설운영구분코드명
    private String idrodrCd;            // 실내외구분코드
    private String idrodrCdNm;          // 실내외구분코드명
    private String exfcDsgnYmd;         // 우수시설지정일
    private String rgnCd;               // 지역분류코드
    private String rgnCdNm;             // 지역분류코드명
    private String latCrtsVl;           // 경도
    private String lotCrtsVl;           // 위도

    // 광주광역시
    private String incld_water;         // 물놀이형 놀이시설
    private String cctvCnt;                // cctv
    private String insurance;           // 보험가입 여부
    private String safetyInsp;          // 안전검사 여부
}
