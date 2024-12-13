package com.HealthBizz.Survey.mapper;

import com.HealthBizz.Survey.dto.UserDto;
import com.HealthBizz.Survey.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmailId(user.getEmailId());
        userDto.setPassword(user.getPassword());
        userDto.setContactNumber(user.getContactNumber());
        userDto.setRole(user.getRole().getName());  // Assuming Role has a name field

        // Set regionName based on the available fields (Country, State, District, etc.)
        if (user.getCountry() != null) {
            userDto.setRegionName(user.getCountry().getName());
        } else if (user.getState() != null) {
            userDto.setRegionName(user.getState().getName());
        } else if (user.getDistrict() != null) {
            userDto.setRegionName(user.getDistrict().getName());
        } else if (user.getTaluka() != null) {
            userDto.setRegionName(user.getTaluka().getName());
        } else if (user.getCity() != null) {
            userDto.setRegionName(user.getCity().getName());
        }

        return userDto;
    }

}
