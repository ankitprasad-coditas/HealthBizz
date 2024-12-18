package com.HealthBizz.Survey.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SurveyData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User dataCollector;

    private String businessName;
    private String businessType;
    private String abhaId;
    private String address;

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
