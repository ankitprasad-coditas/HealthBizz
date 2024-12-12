package com.HealthBizz.Survey.service;

import com.HealthBizz.Survey.dto.UserDto;
import com.HealthBizz.Survey.entity.*;
import com.HealthBizz.Survey.exception.MissingDataException;
import com.HealthBizz.Survey.exception.UnauthorisedException;
import com.HealthBizz.Survey.reporsitory.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final RoleRepo roleRepo;
    private final UserRepo userRepo;
    private final CountryRepo countryRepo;
    private final StateRepo stateRepo;
    private final DistrictRepo districtRepo;
    private final TalukaRepo talukaRepo;
    private final CityRepo cityRepo;
    private final ObjectMapper objectMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(RoleRepo roleRepo, UserRepo userRepo, CountryRepo countryRepo, StateRepo stateRepo, DistrictRepo districtRepo, TalukaRepo talukaRepo, CityRepo cityRepo, ObjectMapper objectMapper, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.countryRepo = countryRepo;
        this.stateRepo = stateRepo;
        this.districtRepo = districtRepo;
        this.talukaRepo = talukaRepo;
        this.cityRepo = cityRepo;
        this.objectMapper = objectMapper;
        this.passwordEncoder = passwordEncoder;
    }


    // Create New User
    public UserDto createUser(UserDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepo.findByEmailId(authentication.getName()).orElseThrow();
        Role currentUserRole = currentUser.getRole();

        if (!canCreateUser(currentUserRole, userDto.getRole())) {
            throw new UnauthorisedException("Unauthorized: Cannot create user with specified role.");
        }

        Role role = roleRepo.findByName(userDto.getRole()).orElseThrow(() -> new MissingDataException("Role Doesn't Exist"));

        Object region = fetchRegionForRole(role.getName(), userDto);

        User newUser = new User();
        newUser.setName(userDto.getName());
        newUser.setEmailId(userDto.getEmailId());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setContactNumber(userDto.getContactNumber());
        newUser.setRole(role);

        assignRegionToUser(newUser, role.getName(), region);

        User savedUser = userRepo.save(newUser);
        UserDto createdUser = objectMapper.convertValue(savedUser, UserDto.class);
        createdUser.setRole(savedUser.getRole().getName());
        createdUser.setCityName(savedUser.getCity().getName());
        createdUser.setTalukaName(savedUser.getTaluka().getName());
        createdUser.setDistrictName(savedUser.getDistrict().getName());
        createdUser.setStateName(savedUser.getState().getName());
        createdUser.setCountryName(savedUser.getCountry().getName());
        return createdUser;
    }

    private boolean canCreateUser(Role currentUserRole, String requestedRole) {
        return switch (currentUserRole.getName().toUpperCase()) {
            case "SUPER_ADMIN" -> true;
            case "ADMIN" -> "STATE_HEAD".equalsIgnoreCase(requestedRole);
            case "STATE_HEAD" -> "DISTRICT_HEAD".equalsIgnoreCase(requestedRole);
            case "DISTRICT_HEAD" -> "TALUKA_HEAD".equalsIgnoreCase(requestedRole);
            case "TALUKA_HEAD" -> "DATA_COLLECTOR".equalsIgnoreCase(requestedRole);
            default -> throw new IllegalStateException("Invalid Role Name: " + currentUserRole.getName().toUpperCase());
        };
    }

    private Object fetchRegionForRole(String roleName, UserDto userDto) {
        return switch (roleName.toUpperCase()) {
            case "ADMIN" -> countryRepo.findByName(userDto.getCountryName())
                    .orElseThrow(() -> new MissingDataException("Invalid country name"));
            case "STATE_HEAD" -> stateRepo.findByName(userDto.getStateName())
                    .orElseThrow(() -> new MissingDataException("Invalid state name"));
            case "DISTRICT_HEAD" -> districtRepo.findByName(userDto.getDistrictName())
                    .orElseThrow(() -> new MissingDataException("Invalid district name"));
            case "TALUKA_HEAD" -> talukaRepo.findByName(userDto.getTalukaName())
                    .orElseThrow(() -> new MissingDataException("Invalid taluka name"));
            case "DATA_COLLECTOR" -> cityRepo.findByName(userDto.getCityName())
                    .orElseThrow(() -> new MissingDataException("Invalid city name"));
            default -> throw new IllegalStateException("Invalid Role Name: " + roleName.toUpperCase());
        };
    }

    private void assignRegionToUser(User user, String roleName, Object region) {
        switch (roleName.toUpperCase()) {
            case "ADMIN" -> user.setCountry((Country) region);
            case "STATE_HEAD" -> user.setState((State) region);
            case "DISTRICT_HEAD" -> user.setDistrict((District) region);
            case "TALUKA_HEAD" -> user.setTaluka((Taluka) region);
            case "DATA_COLLECTOR" -> user.setCity((City) region);
            default -> throw new IllegalStateException("Invalid Role Name: " + roleName.toUpperCase());
        }
    }
}
