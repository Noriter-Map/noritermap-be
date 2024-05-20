package com.noritermap.api.domain.rides.repository;

import com.noritermap.api.domain.rides.entity.Rides;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RidesRepository extends JpaRepository<Rides, Long> {
}
