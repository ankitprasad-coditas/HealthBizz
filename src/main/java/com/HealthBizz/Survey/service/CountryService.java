package com.HealthBizz.Survey.service;

import com.HealthBizz.Survey.dto.CountryDto;
import com.HealthBizz.Survey.entity.Country;
import com.HealthBizz.Survey.reporsitory.CountryRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private ObjectMapper objectMapper;

    public List<CountryDto> viewAllCountries() {
        List<Country> allCountry = countryRepo.findAll();
        List<CountryDto> countryList = allCountry.stream()
                .map(country -> {
                    CountryDto theCountry = objectMapper.convertValue(country,CountryDto.class);
                    return theCountry;
                })
                .collect(Collectors.toList());

        return countryList;
    }
}
