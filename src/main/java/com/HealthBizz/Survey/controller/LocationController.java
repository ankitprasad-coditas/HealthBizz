package com.HealthBizz.Survey.controller;

import com.HealthBizz.Survey.dto.*;
import com.HealthBizz.Survey.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {

    private final LocationService locationService;
    private final CountryService countryService;
    private final StateService stateService;
    private final DistrictService districtService;
    private final TalukaService talukaService;
    private final CityService cityService;

    @Autowired
    public LocationController(LocationService locationService, CountryService countryService, StateService stateService, DistrictService districtService, TalukaService talukaService, CityService cityService) {
        this.locationService = locationService;
        this.countryService = countryService;
        this.stateService = stateService;
        this.districtService = districtService;
        this.talukaService = talukaService;
        this.cityService = cityService;
    }

    @PostMapping("/createLocation")
    public ResponseEntity<ApiResponseDto<Object>> createLocation(@RequestBody LocationDto locationDTO) {
        ApiResponseDto<Object> response = new ApiResponseDto<>(locationService.createLocation(locationDTO), HttpStatus.CREATED.value(),locationDTO.getType().toString()+" Successfully Created");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/regionList")
    public ResponseEntity<List<?>> getRegionListByRole(@RequestParam String roleName){
        List<?> regionList = locationService.getRegionByRole(roleName);
        return ResponseEntity.ok(regionList);
    }

    @GetMapping("/allCountries")
    public ResponseEntity<ApiResponseDto<List<CountryDto>>> getAllCountries(){
        ApiResponseDto<List<CountryDto>> response = new ApiResponseDto<>(countryService.viewAllCountries(), HttpStatus.OK.value(),"Data Fetched Successfully");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/allStates")
    public ResponseEntity<ApiResponseDto<List<StateDto>>> getAllStates(){
        ApiResponseDto<List<StateDto>> response = new ApiResponseDto<>(stateService.viewAllStates(), HttpStatus.OK.value(),"Data Fetched Successfully");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/allDistricts")
    public ResponseEntity<ApiResponseDto<List<DistrictDto>>> getAllDistricts(){
        ApiResponseDto<List<DistrictDto>> response = new ApiResponseDto<>(districtService.viewAllDistricts(), HttpStatus.OK.value(),"Data Fetched Successfully");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/allTalukas")
    public ResponseEntity<ApiResponseDto<List<TalukaDto>>> getAllTalukas(){
        ApiResponseDto<List<TalukaDto>> response = new ApiResponseDto<>(talukaService.viewAllTalukas(), HttpStatus.OK.value(),"Data Fetched Successfully");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/allCities")
    public ResponseEntity<ApiResponseDto<List<CityDto>>> getAllCities(){
        ApiResponseDto<List<CityDto>> response = new ApiResponseDto<>(cityService.viewAllCities(), HttpStatus.OK.value(),"Data Fetched Successfully");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
