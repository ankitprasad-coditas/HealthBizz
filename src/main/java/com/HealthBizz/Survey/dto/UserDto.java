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

    private String password = "test123";

    private Long contactNumber;

    private String role;

    private String regionName;

}
