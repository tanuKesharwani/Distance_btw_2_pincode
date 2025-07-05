package com.assignment.code.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.code.domain.PincodeInfo;

public interface PincodeInfoRepository extends JpaRepository<PincodeInfo, Long> {
    Optional<PincodeInfo> findByPincode(String pincode);
}