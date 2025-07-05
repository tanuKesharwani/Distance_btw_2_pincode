package com.assignment.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.code.pojo.DistanceResponse;
import com.assignment.code.service.DistanceService;

@RestController
@RequestMapping("/distance")
public class DistanceController {

	@Autowired
	private DistanceService distanceService;

	@GetMapping
	public DistanceResponse getDistance(@RequestParam String fromPincode, @RequestParam String toPincode) {
		return distanceService.getDistanceInfo(fromPincode, toPincode);
	}
}
