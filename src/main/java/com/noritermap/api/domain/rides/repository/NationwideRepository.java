package com.noritermap.api.domain.rides.repository;

import com.noritermap.api.domain.rides.entity.NationwideRides;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NationwideRepository extends JpaRepository<NationwideRides, Long> {
    List<NationwideRides> findByPfctNm(String pfctNm);
}
