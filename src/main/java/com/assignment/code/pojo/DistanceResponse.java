package com.assignment.code.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DistanceResponse {

	private String fromPincode;
	
	private String toPincode;
	
	private String distance;
	
	private String duration;
	
	private String routePolyline;

}
