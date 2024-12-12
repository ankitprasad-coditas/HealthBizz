package com.HealthBizz.Survey.service;

import com.HealthBizz.Survey.dto.DistrictDto;
import com.HealthBizz.Survey.entity.District;
import com.HealthBizz.Survey.reporsitory.DistrictRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepo districtRepo;

    @Autowired
    private ObjectMapper objectMapper;

    public List<DistrictDto> viewAllDistricts() {
        List<District> allDistricts = districtRepo.findAll();
        List<DistrictDto> districtList = allDistricts.stream()
                .map(district -> {
                    DistrictDto theDistrict = objectMapper.convertValue(district,DistrictDto.class);
                    return theDistrict;
                })
                .collect(Collectors.toList());

        return districtList;
    }
}
