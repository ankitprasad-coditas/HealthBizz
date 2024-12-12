package com.HealthBizz.Survey.service;

import com.HealthBizz.Survey.dto.SurveyDataDto;
import com.HealthBizz.Survey.entity.Role;
import com.HealthBizz.Survey.entity.SurveyData;
import com.HealthBizz.Survey.entity.User;
import com.HealthBizz.Survey.reporsitory.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurveyDataService {

    private final SurveyDataRepo surveyDataRepo;
    private final UserRepo userRepo;
    private final StateRepo stateRepo;
    private final DistrictRepo districtRepo;
    private final TalukaRepo talukaRepo;
    private final CityRepo cityRepo;
    private final ObjectMapper objectMapper;

    /*@Autowired
    public SurveyDataService(SurveyDataRepo surveyDataRepo, UserRepo userRepo, StateRepo stateRepo, DistrictRepo districtRepo, TalukaRepo talukaRepo, CityRepo cityRepo, ObjectMapper objectMapper) {
        this.surveyDataRepo = surveyDataRepo;
        this.userRepo = userRepo;
        this.stateRepo = stateRepo;
        this.districtRepo = districtRepo;
        this.talukaRepo = talukaRepo;
        this.cityRepo = cityRepo;
        this.objectMapper = objectMapper;
    }*/


    //Create New SurveyData
    public SurveyDataDto newData(SurveyDataDto surveyDataDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepo.findByEmailId(authentication.getName()).orElseThrow();
        Role currentUserRole = currentUser.getRole();

        SurveyData newData = objectMapper.convertValue(surveyDataDto,SurveyData.class);
        newData.setDataCollector(currentUser);
        newData.setCountry(currentUser.getCity().getTaluka().getDistrict().getState().getCountry().getName());
        newData.setState(currentUser.getCity().getTaluka().getDistrict().getState().getName());
        newData.setDistrict(currentUser.getCity().getTaluka().getDistrict().getName());
        newData.setTaluka(currentUser.getCity().getTaluka().getName());
        newData.setCity(currentUser.getCity().getName());

        SurveyData savedData = surveyDataRepo.save(newData);
        return objectMapper.convertValue(savedData, SurveyDataDto.class);
    }

}
