package com.HealthBizz.Survey.controller;

import com.HealthBizz.Survey.dto.LocationDto;
import com.HealthBizz.Survey.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/createLocation")
    public Object createLocation(@RequestBody LocationDto locationDTO) {
        return locationService.createLocation(locationDTO);
    }

    @GetMapping("/regionList")
    public ResponseEntity<List<?>> getRegionListByRole(@RequestParam String roleName){
        List<?> regionList = locationService.getRegionByRole(roleName);
        return ResponseEntity.ok(regionList);
    }
}
