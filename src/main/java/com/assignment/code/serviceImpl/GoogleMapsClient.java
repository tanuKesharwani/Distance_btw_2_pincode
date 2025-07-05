package com.assignment.code.serviceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.assignment.code.pojo.GoogleMapsRouteResponse;

import GoogleMapsApiResponse.GoogleMapsApiResponse;

@Component
public class GoogleMapsClient {

    @Value("${google.maps.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public GoogleMapsRouteResponse getRoute(double fromLat, double fromLon, double toLat, double toLon) {
        String url = String.format(
            "https://maps.googleapis.com/maps/api/directions/json?origin=%f,%f&destination=%f,%f&key=%s",
            fromLat, fromLon, toLat, toLon, apiKey
        );

        GoogleMapsApiResponse apiResponse = restTemplate.getForObject(url, GoogleMapsApiResponse.class);
        if (apiResponse == null || !"OK".equals(apiResponse.status) || apiResponse.routes.isEmpty()) {
            throw new IllegalArgumentException("Unable to fetch route");
        }

        var firstRoute = apiResponse.routes.get(0);
        var firstLeg = firstRoute.legs.get(0);

        return GoogleMapsRouteResponse.builder()
                .distance(firstLeg.distance.text)
                .duration(firstLeg.duration.text)
                .polyline(firstRoute.overviewPolyline.points)
                .build();
    }
}
