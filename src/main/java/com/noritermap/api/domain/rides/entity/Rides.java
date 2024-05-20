package com.noritermap.api.domain.rides.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rides {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RIDES_ID")
    private Long id;

    private String rideSn;              // 놀이기구일련번호
    private String pfctNm;              // 놀이시설명
    private String rideNm;              // 기구명
    private String rideNo;              // 관리번호
    private String rideLctn;            // 설치위치 (설치장소)
    private String rideInstlYmd;        // 설치일자
    private String instlFrmNm;          // 설치업체
    private String inlr;                // 설치자
    private String dscdYmd;             // 폐기일자
    private String dmltnRsn;            // 폐기사유
    private String comaNm;              // 생산국
    private String mnftrFrmNm;          // 제조업체
    private String prdctnYmd;           // 생산일자
    private String certNo;              // 인증번호
    private String rideStylCd;          // 놀이기구유형코드
    private String rideStylCdNm;        // 놀이기구유형코드명
    private String pfctSn;              // 놀이시설일련번호
    private String instlCd;             // 설치검사번호
    private String rgnCd;               // 지역분류코드
    private String rgnNm;               // 지역분류코드명
}