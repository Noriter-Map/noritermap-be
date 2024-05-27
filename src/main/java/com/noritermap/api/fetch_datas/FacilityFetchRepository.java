package com.noritermap.api.fetch_datas;

import com.noritermap.api.domain.facility.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityFetchRepository extends JpaRepository<Facility, Long> {
}
