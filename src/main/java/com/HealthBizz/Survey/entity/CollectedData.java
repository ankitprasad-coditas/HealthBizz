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
public class CollectedData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "data_collector_id", nullable = false)
    private DataCollector dataCollector;

    // Business details
    private String businessName;
    private String businessType;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String contactNumber;
    private String email;

    // Facility details
    private int yearEstablished;
    private String operatingHours;
    private boolean emergencyServices;
    private String specialties;
    private int numberOfBeds;
    private int numberOfConsultationRooms;
    private int numberOfDoctors;
    private int numberOfNurses;
    private int numberOfAdministrativeStaff;


    private LocalDateTime dateOfSurvey;
}
