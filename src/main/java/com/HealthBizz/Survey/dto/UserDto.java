package com.HealthBizz.Survey.dto;

import com.HealthBizz.Survey.entity.City;
import com.HealthBizz.Survey.entity.District;
import com.HealthBizz.Survey.entity.State;
import com.HealthBizz.Survey.entity.Taluka;
import com.HealthBizz.Survey.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    private String name;

    private String emailId;

    private String password;

    private Long contactNumber;

    private Roles role;

    private String location;

    private String state;

    private String district;

    private String taluka;

    private String city;
}
