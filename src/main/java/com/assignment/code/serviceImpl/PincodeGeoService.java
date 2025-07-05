package com.assignment.code.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.assignment.code.domain.PincodeInfo;
import com.assignment.code.repository.PincodeInfoRepository;
@Service
public class PincodeGeoService {

    @Autowired
    private PincodeInfoRepository pincodeRepo;

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String API_KEY = "YOUR_OPENWEATHER_API_KEY";

    public PincodeInfo getOrFetchPincodeInfo(String pincode) {
        return pincodeRepo.findByPincode(pincode)
                .orElseGet(() -> {
                    String geoUrl = String.format(
                        "http://api.openweathermap.org/geo/1.0/zip?zip=%s,in&appid=%s",
                        pincode, API_KEY
                    );
                    var geo = restTemplate.getForObject(geoUrl, GeoResponse.class);
                    if (geo == null) throw new IllegalArgumentException("Invalid pincode");

                    PincodeInfo info = new PincodeInfo();
                    info.setPincode(pincode);
                    info.setLat(geo.lat);
                    info.setLon(geo.lon);
                    pincodeRepo.save(info);
                    return info;
                });
    }

    static class GeoResponse {
        public double lat;
        public double lon;
    }
}

