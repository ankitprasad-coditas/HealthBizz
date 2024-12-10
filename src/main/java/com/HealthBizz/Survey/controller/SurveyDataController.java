package com.HealthBizz.Survey.controller;

import com.HealthBizz.Survey.dto.ApiResponseDto;
import com.HealthBizz.Survey.dto.SurveyDataDto;
import com.HealthBizz.Survey.service.SurveyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/data")
public class SurveyDataController {

    @Autowired
    private SurveyDataService surveyDataService;

    @PostMapping("/newSurveyData")
    public ResponseEntity<ApiResponseDto<SurveyDataDto>> uploadData(@RequestBody SurveyDataDto surveyDataDto){
        SurveyDataDto savedData = surveyDataService.newData(surveyDataDto);
        ApiResponseDto<SurveyDataDto> response = new ApiResponseDto<>(savedData, HttpStatus.CREATED.value(),"SurveyData Saved Successfully");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/allSurveyedData")
    public ResponseEntity<ApiResponseDto<List<SurveyDataDto>>> allSurveyData(){
        return null;
    }

    @GetMapping("/surveyedData/{id}")
    public ResponseEntity<ApiResponseDto<List<SurveyDataDto>>> allSurveyData(@PathVariable Long id){
        return null;
    }
}
