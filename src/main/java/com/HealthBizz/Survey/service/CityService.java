package com.HealthBizz.Survey.service;

import com.HealthBizz.Survey.dto.CityDto;
import com.HealthBizz.Survey.entity.City;
import com.HealthBizz.Survey.reporsitory.CityRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepo cityRepo;

    @Autowired
    private ObjectMapper objectMapper;

    public List<CityDto> viewAllCities() {
        List<City> allCities = cityRepo.findAll();
        List<CityDto> cityList = allCities.stream()
                .map(city -> {
                    CityDto theCity = objectMapper.convertValue(city,CityDto.class);
                    return theCity;
                })
                .collect(Collectors.toList());

        return cityList;
    }
}
