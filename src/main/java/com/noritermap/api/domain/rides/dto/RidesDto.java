package com.noritermap.api.domain.rides.dto;

import com.noritermap.api.domain.rides.entity.Rides;
import lombok.Builder;
import lombok.Data;
import org.json.simple.JSONObject;

public class RidesDto {

    @Data
    @Builder
    public static class RidesResponseDto{
        private String rideSn;
        private String pfctNm;
        private String rideNm;
        private String rideNo;
        private String rideLctn;
        private String rideInstlYmd;
        private String instlFrmNm;
        private String inlr;
        private String dscdYmd;
        private String dmltnRsn;
        private String comaNm;
        private String mnftrFrmNm;
        private String prdctnYmd;
        private String certNo;
        private String rideStylCd;
        private String rideStylCdNm;
        private String pfctSn;
        private String instlCd;
        private String rgnCd;
        private String rgnNm;

        public static RidesResponseDto fromJsonObject(JSONObject item) {
            return RidesResponseDto.builder()
                    .rideSn((String) item.get("rideSn"))
                    .pfctNm((String) item.get("pfctNm"))
                    .rideNm((String) item.get("rideNm"))
                    .rideNo((String) item.get("rideNo"))
                    .rideLctn((String) item.get("rideLctn"))
                    .rideInstlYmd((String) item.get("rideInstlYmd"))
                    .instlFrmNm((String) item.get("instlFrmNm"))
                    .inlr((String) item.get("inlr"))
                    .dscdYmd((String) item.get("dscdYmd"))
                    .dmltnRsn((String) item.get("dmltnRsn"))
                    .comaNm((String) item.get("comaNm"))
                    .mnftrFrmNm((String) item.get("mnftrFrmNm"))
                    .prdctnYmd((String) item.get("prdctnYmd"))
                    .certNo((String) item.get("certNo"))
                    .rideStylCd((String) item.get("rideStylCd"))
                    .rideStylCdNm((String) item.get("rideStylCdNm"))
                    .pfctSn((String) item.get("pfctSn"))
                    .instlCd((String) item.get("instlCd"))
                    .rgnCd((String) item.get("rgnCd"))
                    .rgnNm((String) item.get("rgnNm"))
                    .build();
        }

        public Rides toEntity() {
            return Rides.builder()
                    .rideSn(this.rideSn)
                    .pfctNm(this.pfctNm)
                    .rideNm(this.rideNm)
                    .rideNo(this.rideNo)
                    .rideLctn(this.rideLctn)
                    .rideInstlYmd(this.rideInstlYmd)
                    .instlFrmNm(this.instlFrmNm)
                    .inlr(this.inlr)
                    .dscdYmd(this.dscdYmd)
                    .dmltnRsn(this.dmltnRsn)
                    .comaNm(this.comaNm)
                    .mnftrFrmNm(this.mnftrFrmNm)
                    .prdctnYmd(this.prdctnYmd)
                    .certNo(this.certNo)
                    .rideStylCd(this.rideStylCd)
                    .rideStylCdNm(this.rideStylCdNm)
                    .pfctSn(this.pfctSn)
                    .instlCd(this.instlCd)
                    .rgnCd(this.rgnCd)
                    .rgnNm(this.rgnNm)
                    .build();
        }
    }
}
