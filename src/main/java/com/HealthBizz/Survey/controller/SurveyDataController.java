package com.HealthBizz.Survey.controller;

import com.HealthBizz.Survey.dto.ApiResponseDto;
import com.HealthBizz.Survey.dto.SurveyDataDto;
import com.HealthBizz.Survey.entity.SurveyData;
import com.HealthBizz.Survey.service.SurveyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/data")
public class SurveyDataController {

    @Autowired
    private SurveyDataService surveyDataService;

    @PostMapping("/newSurveyData")
//    @PreAuthorize()
    public ResponseEntity<ApiResponseDto<SurveyDataDto>> uploadData(@RequestBody SurveyDataDto surveyDataDto) {
        SurveyDataDto savedData = surveyDataService.newData(surveyDataDto);
        ApiResponseDto<SurveyDataDto> response = new ApiResponseDto<>(savedData, HttpStatus.CREATED.value(), "SurveyData Saved Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/allSurveyedData")
    public ResponseEntity<ApiResponseDto<List<SurveyDataDto>>> allSurveyData() {
        List<SurveyDataDto> surveyDataList = surveyDataService.allCollectedData();
        ApiResponseDto<List<SurveyDataDto>> apiResponseDto = new ApiResponseDto<>(surveyDataList, HttpStatus.OK.value(), "Data Fetched Successfully");
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }


    @GetMapping("/surveyedData")
    public ResponseEntity<ApiResponseDto<List<SurveyDataDto>>> allSurveyDataById() {
        List<SurveyDataDto> surveyDataList = surveyDataService.dataByCollectorId();
        ApiResponseDto<List<SurveyDataDto>> apiResponseDto = new ApiResponseDto<>(surveyDataList, HttpStatus.OK.value(), "Data Fetched Successfully");
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
}
