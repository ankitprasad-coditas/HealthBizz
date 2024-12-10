package com.HealthBizz.Survey.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class SurveyDataDto {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDataCollectorId() {
        return dataCollectorId;
    }

    public void setDataCollectorId(Long dataCollectorId) {
        this.dataCollectorId = dataCollectorId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getAbhaId() {
        return abhaId;
    }

    public void setAbhaId(String abhaId) {
        this.abhaId = abhaId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTaluka() {
        return taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadDoctor() {
        return headDoctor;
    }

    public void setHeadDoctor(String headDoctor) {
        this.headDoctor = headDoctor;
    }

    public int getYearEstablished() {
        return yearEstablished;
    }

    public void setYearEstablished(int yearEstablished) {
        this.yearEstablished = yearEstablished;
    }

    public boolean isEmergencyServices() {
        return emergencyServices;
    }

    public void setEmergencyServices(boolean emergencyServices) {
        this.emergencyServices = emergencyServices;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public int getNumberOfDoctors() {
        return numberOfDoctors;
    }

    public void setNumberOfDoctors(int numberOfDoctors) {
        this.numberOfDoctors = numberOfDoctors;
    }

    public LocalDateTime getDateOfSurvey() {
        return dateOfSurvey;
    }

    public void setDateOfSurvey(LocalDateTime dateOfSurvey) {
        this.dateOfSurvey = LocalDateTime.now();
    }
}
