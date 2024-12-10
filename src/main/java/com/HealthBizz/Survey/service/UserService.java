package com.HealthBizz.Survey.service;

import com.HealthBizz.Survey.dto.UserDto;
import com.HealthBizz.Survey.entity.User;
import com.HealthBizz.Survey.enums.Roles;
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

    private final UserRepo userRepo;
    private final StateRepo stateRepo;
    private final DistrictRepo districtRepo;
    private final TalukaRepo talukaRepo;
    private final CityRepo cityRepo;
    private final ObjectMapper objectMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, StateRepo stateRepo, DistrictRepo districtRepo, TalukaRepo talukaRepo, CityRepo cityRepo, ObjectMapper objectMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
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
        Roles currentUserRole = currentUser.getRole();

        if (!canCreateUser(currentUserRole, userDto.getRole())) {
            throw new UnauthorisedException("Unauthorized: Cannot create user with specified role.");
        }

        User newUser = new User();
        newUser.setName(userDto.getName());
        newUser.setEmailId(userDto.getEmailId());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setContactNumber(userDto.getContactNumber());
        newUser.setRole(userDto.getRole());
        newUser.setLocation(userDto.getLocation());


        if (userDto.getStateName() != null) newUser.setState(stateRepo.findByName(userDto.getStateName()).orElseThrow());
        if (userDto.getDistrictName() != null) newUser.setDistrict(districtRepo.findByName(userDto.getDistrictName()).orElseThrow());
        if (userDto.getTalukaName() != null) newUser.setTaluka(talukaRepo.findByName(userDto.getTalukaName()).orElseThrow());
        if (userDto.getCityName() != null) newUser.setCity(cityRepo.findByName(userDto.getCityName()).orElseThrow());

        return objectMapper.convertValue(userRepo.save(newUser),UserDto.class);
    }

    private boolean canCreateUser(Roles currentUserRole, Roles requestedRole) {
        return switch (currentUserRole) {
            case SUPER_ADMIN -> requestedRole == Roles.ADMIN;
            case ADMIN -> requestedRole == Roles.STATE_HEAD;
            case STATE_HEAD -> requestedRole == Roles.DISTRICT_HEAD;
            case DISTRICT_HEAD -> requestedRole == Roles.TALUKA_HEAD;
            case TALUKA_HEAD -> requestedRole == Roles.DATA_COLLECTOR;
            default -> false;
        };
    }
}
