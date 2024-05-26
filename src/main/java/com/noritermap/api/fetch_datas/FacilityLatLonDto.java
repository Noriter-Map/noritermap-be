package com.noritermap.api.fetch_datas;

import com.noritermap.api.domain.facility.entity.Facility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacilityLatLonDto {
    Long facilityId;
    String latitude;
    String longitude;
    String facilityName;
    String addr;

    public static FacilityLatLonDto from(Facility facility){
        return new FacilityLatLonDto(facility.getId(), facility.getLatCrtsVl(), facility.getLotCrtsVl(), facility.getPfctNm(), facility.getRonaAddr());
    }
}
