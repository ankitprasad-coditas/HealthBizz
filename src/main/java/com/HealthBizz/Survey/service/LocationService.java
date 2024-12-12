package com.HealthBizz.Survey.service;

import com.HealthBizz.Survey.dto.CountryDto;
import com.HealthBizz.Survey.dto.DistrictDto;
import com.HealthBizz.Survey.dto.LocationDto;
import com.HealthBizz.Survey.dto.StateDto;
import com.HealthBizz.Survey.entity.*;
import com.HealthBizz.Survey.exception.DuplicateDataException;
import com.HealthBizz.Survey.reporsitory.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    //    @Autowired
    private final UserRepo userRepo;

    //    @Autowired
    private final CountryRepo countryRepo;
    //    @Autowired
    private final StateRepo stateRepo;

    //    @Autowired
    private final DistrictRepo districtRepo;

    //    @Autowired
    private final TalukaRepo talukaRepo;

    //    @Autowired
    private final CityRepo cityRepo;

    //    @Autowired
    private final ObjectMapper objectMapper;

    public Object createLocation(LocationDto locationDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepo.findByEmailId(authentication.getName()).orElseThrow();
        Role role = currentUser.getRole();

        String type = locationDTO.getType().toString().toLowerCase();
        String name = locationDTO.getName();

        switch (role.getName()) {
            case "SUPER_ADMIN":
                return createLocation(type, name, currentUser);

            case "ADMIN":
                if ("country".equals(type)) {
                    throw new IllegalArgumentException("Admin cannot create a country");
                }
                return createLocation(type, name, currentUser);

            case "STATE_HEAD":
                if ("country".equals(type) || "state".equals(type)) {
                    throw new IllegalArgumentException("State head cannot create a country or state");
                }
                return createLocation(type, name, currentUser);

            case "DISTRICT_HEAD":
                if ("country".equals(type) || "state".equals(type) || "district".equals(type)) {
                    throw new IllegalArgumentException("District head cannot create a country or state or districts");
                }
                return createLocation(type, name, currentUser);

            case "TALUKA_HEAD":
                if ("country".equals(type) || "state".equals(type) || "district".equals(type) || "taluka".equals(type)) {
                    throw new IllegalArgumentException("Taluka head cannot create a country or state or districts or taluka");
                }
                return createLocation(type, name, currentUser);

            case "DATA_COLLECTOR":
                if (!type.isBlank()) {
                    throw new IllegalArgumentException("Data Collector Cannot Create Region");
                }
                return null;

            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }
    }

    private Object createLocation(String type, String name, User parentUser) {
        switch (type) {
            case "country":
                if (countryRepo.findByName(name).isPresent()) {
                    throw new DuplicateDataException("Country Already Exists");
                }
                Country country = new Country();
                country.setName(name);
                return objectMapper.convertValue(countryRepo.save(country), CountryDto.class);

            case "state":
                if (stateRepo.findByStateNameAndCountryId(name, parentUser.getCountry().getId()).isPresent()) {
                    throw new DuplicateDataException("State Already Exists");
                }

                State state = new State();
                state.setName(name);
                state.setCountry(parentUser.getCountry());
                return objectMapper.convertValue(stateRepo.save(state), StateDto.class);

            case "district":
                if (districtRepo.findByDistrictNameAndStateId(name, parentUser.getState().getId()).isPresent()) {
                    throw new DuplicateDataException("District Already Exists");
                }

                District district = new District();
                district.setName(name);
                district.setState(parentUser.getState());
                District savedDistrict = districtRepo.save(district);
                DistrictDto districtDto = objectMapper.convertValue(savedDistrict, DistrictDto.class);
                districtDto.setStateName(savedDistrict.getState().getName());
                return districtDto;

            case "taluka":
                if(talukaRepo.findByTalukaNameAndDistrictId(name,parentUser.getDistrict().getId()).isPresent()){
                    throw new DuplicateDataException("Taluka Already Present");
                }

                Taluka taluka = new Taluka();
                taluka.setName(name);
                taluka.setDistrict(parentUser.getDistrict());
                return talukaRepo.save(taluka);

            case "city":
                if(cityRepo.findByCityNameAndTalukaId(name,parentUser.getTaluka().getId()).isPresent()){
                    throw new DuplicateDataException("City Already Present");
                }

                City city = new City();
                city.setName(name);
                city.setTaluka(parentUser.getTaluka());
                return cityRepo.save(city);

            default:
                throw new IllegalArgumentException("Invalid location type: " + type);
        }
    }

    public List<?> getRegionByRole(String roleName) {

        List<?> regionList = switch (roleName.toUpperCase()){

            case "ADMIN"-> countryRepo.findAll();
            case "STATE_HEAD"->stateRepo.findAllByNoHead();
            case "DISTRICT_HEAD"->districtRepo.findAllByNoHead();
            case "TALUKA_HEAD"->talukaRepo.findAllByNoHead();
            case "DATA_COLLECTOR"->cityRepo.findAllByNoDataCollector();
            default -> throw new IllegalStateException("Invalid Role Name: " + roleName.toUpperCase());
        };
        return regionList;
    }
}
