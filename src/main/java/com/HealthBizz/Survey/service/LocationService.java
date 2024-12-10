package com.HealthBizz.Survey.service;

import com.HealthBizz.Survey.dto.CountryDto;
import com.HealthBizz.Survey.dto.DistrictDto;
import com.HealthBizz.Survey.dto.LocationDto;
import com.HealthBizz.Survey.dto.StateDto;
import com.HealthBizz.Survey.entity.*;
import com.HealthBizz.Survey.enums.Roles;
import com.HealthBizz.Survey.reporsitory.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CountryRepo countryRepository;

    @Autowired
    private StateRepo stateRepository;

    @Autowired
    private DistrictRepo districtRepository;

    @Autowired
    private TalukaRepo talukaRepository;

    @Autowired
    private CityRepo cityRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public Object createLocation(LocationDto locationDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepo.findByEmailId(authentication.getName()).orElseThrow();
        Roles role = currentUser.getRole();

        String type = locationDTO.getType().toLowerCase();
        String name = locationDTO.getName();

        switch (role) {
            case SUPER_ADMIN:
                return createEntity(type, name);

            case ADMIN:
                if ("country".equals(type)) {
                    throw new IllegalArgumentException("Admin cannot create a country");
                }
                return createEntity(type, name);
            case STATE_HEAD:
                if ("country".equals(type) || "state".equals(type)) {
                    throw new IllegalArgumentException("State head cannot create a country or state");
                }
                return createEntity(type, name);
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }
    }

    private Object createEntity(String type, String name) {
        switch (type) {
            case "country":
                Country country = new Country();
                country.setName(name);
                return objectMapper.convertValue(countryRepository.save(country), CountryDto.class);
            case "state":
                State state = new State();
                state.setName(name);
                return objectMapper.convertValue(stateRepository.save(state), StateDto.class);
            case "district":
                District district = new District();
                district.setName(name);
                return objectMapper.convertValue(districtRepository.save(district), DistrictDto.class);
            case "taluka":
                Taluka taluka = new Taluka();
                taluka.setName(name);
                return talukaRepository.save(taluka);
            case "city":
                City city = new City();
                city.setName(name);
                return cityRepository.save(city);
            default:
                throw new IllegalArgumentException("Invalid location type: " + type);
        }
    }


    /*public Object createLocation(LocationDto locationDTO) {
        String type = locationDTO.getType().toLowerCase();
        String name = locationDTO.getName();

        switch (type) {
            case "country":
                Country country = new Country();
                country.setName(name);
                return objectMapper.convertValue(countryRepository.save(country), CountryDto.class);
            case "state":
                State state = new State();
                state.setName(name);
                return objectMapper.convertValue(stateRepository.save(state), StateDto.class);
            case "district":
                District district = new District();
                district.setName(name);
                return objectMapper.convertValue(districtRepository.save(district), DistrictDto.class);
            case "taluka":
                Taluka taluka = new Taluka();
                taluka.setName(name);
                return talukaRepository.save(taluka);
            case "city":
                City city = new City();
                city.setName(name);
                cityRepository.save(city);
            default:
                throw new IllegalArgumentException("Invalid location type: " + type);
        }
    }*/




}
