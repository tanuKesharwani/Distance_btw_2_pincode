package com.assignment.code.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.code.domain.DistanceInfo;

public interface DistanceInfoRepository extends JpaRepository<DistanceInfo, Long> {
    Optional<DistanceInfo> findByFromPincodeAndToPincode(String fromPincode, String toPincode);
}