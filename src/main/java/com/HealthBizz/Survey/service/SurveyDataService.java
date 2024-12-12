package com.HealthBizz.Survey.service;

import com.HealthBizz.Survey.dto.SurveyDataDto;
import com.HealthBizz.Survey.entity.SurveyData;
import com.HealthBizz.Survey.entity.User;
import com.HealthBizz.Survey.mapper.SurveyDataMapper;
import com.HealthBizz.Survey.reporsitory.SurveyDataRepo;
import com.HealthBizz.Survey.reporsitory.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyDataService {

    private final SurveyDataRepo surveyDataRepo;
    private final UserRepo userRepo;
    private final SurveyDataMapper surveyDataMapper;

    //Create New SurveyData
    public SurveyDataDto newData(SurveyDataDto surveyDataDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepo.findByEmailId(authentication.getName()).orElseThrow();
        SurveyData newSurveyedData = surveyDataMapper.convertToEntity(surveyDataDto, currentUser);
        SurveyData savedData = surveyDataRepo.save(newSurveyedData);

        return surveyDataMapper.convertToDto(savedData);
    }

    public List<SurveyDataDto> dataByCollectorId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepo.findByEmailId(authentication.getName()).orElseThrow();
        List<SurveyData> surveyDataList = surveyDataRepo.findAllByDataCollectorId(currentUser.getId());
        return surveyDataList.stream()
                .map(surveyDataMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public List<SurveyDataDto> allCollectedData(){
        List<SurveyData> surveyDataList = surveyDataRepo.findAll();
        return surveyDataList.stream()
                .map(surveyDataMapper::convertToDto)
                .collect(Collectors.toList());
    }
}
