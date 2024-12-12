package com.HealthBizz.Survey.dto;

import com.HealthBizz.Survey.enums.LocationTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocationDto {

    private LocationTypes type;
    private String name;
    private String parentLocation;

}
