package com.HealthBizz.Survey.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SurveyDataDto {
    private Long id;
    private Long dataCollectorId;
    private String businessName;
    private String businessType;
    private String address;
    private String city;
    private String state;
    private String contactNumber;
    private String email;

    private String headDoctor;
    private int yearEstablished;
    private boolean emergencyServices;
    private int numberOfBeds;
    private int numberOfDoctors;

    private LocalDateTime dateOfSurvey;

    public LocalDateTime getDateOfSurvey() {
        return dateOfSurvey;
    }

    public void setDateOfSurvey(LocalDateTime dateOfSurvey) {
        this.dateOfSurvey = LocalDateTime.now();
    }
}
