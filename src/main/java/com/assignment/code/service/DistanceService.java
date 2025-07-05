package com.assignment.code.service;

import com.assignment.code.pojo.DistanceResponse;

public interface DistanceService {

	public DistanceResponse getDistanceInfo(String fromPincode, String toPincode);
	
}
