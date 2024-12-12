package com.HealthBizz.Survey.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;

    private String name;

    private String emailId;

    private String password;

    private Long contactNumber;

    private String role;

    private String countryName ;

    private String stateName ;

    private String districtName;

    private String talukaName;

    private String cityName;

}
