package com.HealthBizz.Survey.dto;

import com.HealthBizz.Survey.enums.LocationTypes;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocationDto {

    @Enumerated(EnumType.STRING)
    private LocationTypes type;

    private String name;

}
