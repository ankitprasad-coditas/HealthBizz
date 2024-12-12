package com.HealthBizz.Survey.mapper;

import com.HealthBizz.Survey.dto.SurveyDataDto;
import com.HealthBizz.Survey.entity.SurveyData;
import com.HealthBizz.Survey.entity.User;
import org.springframework.stereotype.Component;

@Component
public class SurveyDataMapper {

    public SurveyData convertToEntity(SurveyDataDto surveyDataDto, User user) {
        SurveyData surveyData = new SurveyData();
        surveyData.setBusinessName(surveyDataDto.getBusinessName());
        surveyData.setBusinessType(surveyDataDto.getBusinessType());
        surveyData.setAbhaId(surveyDataDto.getAbhaId());
        surveyData.setAddress(surveyDataDto.getAddress());

        surveyData.setDataCollector(user);
        surveyData.setCountry(user.getCity().getTaluka().getDistrict().getState().getCountry().getName());
        surveyData.setState(user.getCity().getTaluka().getDistrict().getState().getName());
        surveyData.setDistrict(user.getCity().getTaluka().getDistrict().getName());
        surveyData.setTaluka(user.getCity().getTaluka().getName());
        surveyData.setCity(user.getCity().getName());

        surveyData.setContactNumber(surveyDataDto.getContactNumber());
        surveyData.setEmail(surveyDataDto.getEmail());
        surveyData.setHeadDoctor(surveyDataDto.getHeadDoctor());
        surveyData.setYearEstablished(surveyDataDto.getYearEstablished());
        surveyData.setEmergencyServices(surveyDataDto.isEmergencyServices());
        surveyData.setNumberOfBeds(surveyDataDto.getNumberOfBeds());
        surveyData.setNumberOfDoctors(surveyDataDto.getNumberOfDoctors());
        return surveyData;
    }

    public SurveyDataDto convertToDto(SurveyData surveyData) {
        SurveyDataDto surveyDataDto = new SurveyDataDto();

        surveyDataDto.setBusinessName(surveyData.getBusinessName());
        surveyDataDto.setBusinessType(surveyData.getBusinessType());
        surveyDataDto.setAbhaId(surveyData.getAbhaId());
        surveyDataDto.setAddress(surveyData.getAddress());

        surveyDataDto.setDataCollectorId(surveyData.getDataCollector().getId());
        surveyDataDto.setCountry(surveyData.getCountry());
        surveyDataDto.setState(surveyData.getState());
        surveyDataDto.setDistrict(surveyData.getDistrict());
        surveyDataDto.setTaluka(surveyData.getTaluka());
        surveyDataDto.setCity(surveyData.getCity());

        surveyDataDto.setContactNumber(surveyData.getContactNumber());
        surveyDataDto.setEmail(surveyData.getEmail());
        surveyDataDto.setHeadDoctor(surveyData.getHeadDoctor());
        surveyDataDto.setYearEstablished(surveyData.getYearEstablished());
        surveyDataDto.setEmergencyServices(surveyData.isEmergencyServices());
        surveyDataDto.setNumberOfBeds(surveyData.getNumberOfBeds());
        surveyDataDto.setNumberOfDoctors(surveyData.getNumberOfDoctors());
        surveyDataDto.setDateOfSurvey(surveyData.getDateOfSurvey());
        return surveyDataDto;
    }

}
