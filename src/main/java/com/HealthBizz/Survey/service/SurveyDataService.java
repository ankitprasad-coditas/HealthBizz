package com.HealthBizz.Survey.service;

import com.HealthBizz.Survey.dto.SurveyDataDto;
import com.HealthBizz.Survey.entity.SurveyData;
import com.HealthBizz.Survey.entity.User;
import com.HealthBizz.Survey.reporsitory.SurveyDataRepo;
import com.HealthBizz.Survey.reporsitory.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyDataService {

    private final SurveyDataRepo surveyDataRepo;
    private final UserRepo userRepo;
    private final ObjectMapper objectMapper;

    @Autowired
    public SurveyDataService(SurveyDataRepo surveyDataRepo, UserRepo userRepo, ObjectMapper objectMapper) {
        this.surveyDataRepo = surveyDataRepo;
        this.userRepo = userRepo;
        this.objectMapper = objectMapper;
    }

    //Create New SurveyData
    public SurveyDataDto newData(SurveyDataDto surveyDataDto){
        SurveyData newData = objectMapper.convertValue(surveyDataDto,SurveyData.class);
        SurveyData savedData = surveyDataRepo.save(newData);
        return objectMapper.convertValue(savedData, SurveyDataDto.class);
    }

}
