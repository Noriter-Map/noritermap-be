package com.noritermap.api.domain.facility.dto;

import com.noritermap.api.domain.facility.entity.Facility;
import lombok.Builder;
import lombok.Data;
import org.json.simple.JSONObject;

public class FacilityDto {

    @Data
    @Builder
    public static class FacilityResponseDto{
        private String pfctSn;
        private String pfctNm;
        private String zip;
        private String lotnoAddr;
        private String lotnoDaddr;
        private String ronaAddr;
        private String ronaDaddr;
        private String instlYmd;
        private String clsgYmd;
        private String acptnYmd;
        private String etcSufa;
        private String exfcYn;
        private String fcar;
        private String instlPlaceCd;
        private String instlPlaceCdNm;
        private String dutyCd;
        private String dutyCdNm;
        private String prvtPblcYnCd;
        private String prvtPblcYnCdNm;
        private String operYnCd;
        private String operYnCdNm;
        private String idrodrCd;
        private String idrodrCdNm;
        private String exfcDsgnYmd;
        private String rgnCd;
        private String rgnCdNm;
        private String latCrtsVl;
        private String lotCrtsVl;

        public static FacilityResponseDto fromJsonObject(JSONObject item){
            return FacilityResponseDto.builder()
                    .pfctSn((String) item.get("pfctSn"))
                    .pfctNm((String) item.get("pfctNm"))
                    .zip((String) item.get("zip"))
                    .lotnoAddr((String) item.get("lotnoAddr"))
                    .lotnoDaddr((String) item.get("lotnoDaddr"))
                    .ronaAddr((String) item.get("ronaAddr"))
                    .ronaDaddr((String) item.get("ronaDaddr"))
                    .instlYmd((String) item.get("instlYmd"))
                    .clsgYmd((String) item.get("clsgYmd"))
                    .acptnYmd((String) item.get("acptnYmd"))
                    .etcSufa((String) item.get("etcSufa"))
                    .exfcYn((String) item.get("exfcYn"))
                    .fcar((String) item.get("fcar"))
                    .instlPlaceCd((String) item.get("instlPlaceCd"))
                    .instlPlaceCdNm((String) item.get("instlPlaceCdNm"))
                    .dutyCd((String) item.get("dutyCd"))
                    .dutyCdNm((String) item.get("dutyCdNm"))
                    .prvtPblcYnCd((String) item.get("prvtPblcYnCd"))
                    .prvtPblcYnCdNm((String) item.get("prvtPblcYnCdNm"))
                    .operYnCd((String) item.get("operYnCd"))
                    .operYnCdNm((String) item.get("operYnCdNm"))
                    .idrodrCd((String) item.get("idrodrCd"))
                    .idrodrCdNm((String) item.get("idrodrCdNm"))
                    .exfcDsgnYmd((String) item.get("exfcDsgnYmd"))
                    .rgnCd((String) item.get("rgnCd"))
                    .rgnCdNm((String) item.get("rgnCdNm"))
                    .latCrtsVl((String) item.get("latCrtsVl"))
                    .lotCrtsVl((String) item.get("lotCrtsVl"))
                    .build();
        }

        public Facility toEntity() {
            return Facility.builder()
                    .pfctSn(this.pfctSn)
                    .pfctNm(this.pfctNm)
                    .zip(this.zip)
                    .lotnoAddr(this.lotnoAddr)
                    .lotnoDaddr(this.lotnoDaddr)
                    .ronaAddr(this.ronaAddr)
                    .ronaDaddr(this.ronaDaddr)
                    .instlYmd(this.instlYmd)
                    .clsgYmd(this.clsgYmd)
                    .acptnYmd(this.acptnYmd)
                    .etcSufa(this.etcSufa)
                    .exfcYn(this.exfcYn)
                    .fcar(this.fcar)
                    .instlPlaceCd(this.instlPlaceCd)
                    .instlPlaceCdNm(this.instlPlaceCdNm)
                    .dutyCd(this.dutyCd)
                    .dutyCdNm(this.dutyCdNm)
                    .prvtPblcYnCd(this.prvtPblcYnCd)
                    .prvtPblcYnCdNm(this.prvtPblcYnCdNm)
                    .operYnCd(this.operYnCd)
                    .operYnCdNm(this.operYnCdNm)
                    .idrodrCd(this.idrodrCd)
                    .idrodrCdNm(this.idrodrCdNm)
                    .exfcDsgnYmd(this.exfcDsgnYmd)
                    .rgnCd(this.rgnCd)
                    .rgnCdNm(this.rgnCdNm)
                    .latCrtsVl(this.latCrtsVl)
                    .lotCrtsVl(this.lotCrtsVl)
                    .build();
        }
    }
}
