package com.noritermap.api.domain.facility.repository;

import com.noritermap.api.domain.facility.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Long>, FacilityRepositoryCustom {
}
