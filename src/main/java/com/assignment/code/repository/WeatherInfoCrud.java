package com.assignment.code.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.code.domain.DistanceInfo;

public interface WeatherInfoCrud extends JpaRepository<DistanceInfo, Long> {
	
	Optional<DistanceInfo> findByPincodeAndForDate(String pincode, LocalDate forDate);
}
