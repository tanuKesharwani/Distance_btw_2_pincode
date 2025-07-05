package com.assignment.code.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoogleMapsRouteResponse {
	private String distance;
	private String duration;
	private String polyline;
}
