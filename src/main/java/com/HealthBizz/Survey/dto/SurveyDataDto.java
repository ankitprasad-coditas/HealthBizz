package com.HealthBizz.Survey.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SurveyDataDto {
    @JsonIgnore
    private Long id;

    private Long dataCollectorId;
    private String businessName;
    private String businessType;
    private String abhaId;
    private String address;

    //Not Populated by FE
    private String city;
    private String taluka;
    private String district;
    private String state;
    private String country;

    private String contactNumber;
    private String email;

    private String headDoctor;
    private int yearEstablished;
    private boolean emergencyServices;
    private int numberOfBeds;
    private int numberOfDoctors;

    private LocalDateTime dateOfSurvey;

}