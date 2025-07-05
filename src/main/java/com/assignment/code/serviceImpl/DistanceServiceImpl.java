package com.assignment.code.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.code.domain.DistanceInfo;
import com.assignment.code.domain.PincodeInfo;
import com.assignment.code.pojo.DistanceResponse;
import com.assignment.code.pojo.GoogleMapsRouteResponse;
import com.assignment.code.repository.DistanceInfoRepository;
import com.assignment.code.service.DistanceService;

@Service
public class DistanceServiceImpl implements DistanceService{

    @Autowired
    private DistanceInfoRepository distanceInfoRepo;

    @Autowired
    private PincodeGeoService pincodeGeoService;

    @Autowired
    private GoogleMapsClient googleMapsClient;

    @Override
    public DistanceResponse getDistanceInfo(String fromPincode, String toPincode) {
        // 1. check DB cache
        Optional<DistanceInfo> cached = distanceInfoRepo.findByFromPincodeAndToPincode(fromPincode, toPincode);
        if (cached.isPresent()) {
            return toResponse(cached.get());
        }

        // 2. get lat/lon from Pincode
        PincodeInfo fromInfo = pincodeGeoService.getOrFetchPincodeInfo(fromPincode);
        PincodeInfo toInfo = pincodeGeoService.getOrFetchPincodeInfo(toPincode);

        // 3. call Google Maps
        GoogleMapsRouteResponse route = googleMapsClient.getRoute(
            fromInfo.getLat(), fromInfo.getLon(),
            toInfo.getLat(), toInfo.getLon()
        );

        // 4. store in DB
        DistanceInfo info = new DistanceInfo();
        info.setFromPincode(fromPincode);
        info.setToPincode(toPincode);
        info.setDistance(route.getDistance());
        info.setDuration(route.getDuration());
        info.setRoutePolyline(route.getPolyline());
        distanceInfoRepo.save(info);

        return toResponse(info);
    }

    private DistanceResponse toResponse(DistanceInfo info) {
        return DistanceResponse.builder()
                .fromPincode(info.getFromPincode())
                .toPincode(info.getToPincode())
                .distance(info.getDistance())
                .duration(info.getDuration())
                .routePolyline(info.getRoutePolyline())
                .build();
    }
}