package com.HealthBizz.Survey.service;

import com.HealthBizz.Survey.dto.UserDto;
import com.HealthBizz.Survey.exception.DuplicateDataException;
import com.HealthBizz.Survey.reporsitory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final StateRepo stateRepo;
    private final DistrictRepo districtRepo;
    private final TalukaRepo talukaRepo;
    private final CityRepo cityRepo;

    @Autowired
    public UserService(UserRepo userRepo, StateRepo stateRepo, DistrictRepo districtRepo, TalukaRepo talukaRepo, CityRepo cityRepo) {
        this.userRepo = userRepo;
        this.stateRepo = stateRepo;
        this.districtRepo = districtRepo;
        this.talukaRepo = talukaRepo;
        this.cityRepo = cityRepo;
    }

    public UserDto createUser(UserDto userDto){
        if(userRepo.findByEmailId(userDto.getEmailId()).isPresent()){
            throw new DuplicateDataException("User Already Exists");
        }
        else{

        }
    }
}
