package com.HealthBizz.Survey.service;

import com.HealthBizz.Survey.dto.UserDto;
import com.HealthBizz.Survey.entity.*;
import com.HealthBizz.Survey.exception.MissingDataException;
import com.HealthBizz.Survey.exception.UnauthorisedException;
import com.HealthBizz.Survey.mapper.UserMapper;
import com.HealthBizz.Survey.reporsitory.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RoleRepo roleRepo;
    private final UserRepo userRepo;
    private final CountryRepo countryRepo;
    private final StateRepo stateRepo;
    private final DistrictRepo districtRepo;
    private final TalukaRepo talukaRepo;
    private final CityRepo cityRepo;
    private final UserMapper userMapper;
    private final JavaMailSender javaMailSender;
    private final BCryptPasswordEncoder passwordEncoder;


    // Create New User
    public UserDto createUser(UserDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepo.findByEmailId(authentication.getName()).orElseThrow();
        Role currentUserRole = currentUser.getRole();

        if (!canCreateUser(currentUserRole, userDto.getRole())) {
            throw new UnauthorisedException("Unauthorized: Cannot create user with specified role.");
        }

        Role role = roleRepo.findByName(userDto.getRole()).orElseThrow(() -> new MissingDataException("Role Doesn't Exist"));

        if (isUserHeadOfAnyRegion(userDto.getEmailId())) {
            throw new UnauthorisedException("User cannot be head of more than one place.");
        }

        Object region = fetchRegionForRole(role.getName(), userDto);

        if (regionAlreadyHasHead(region)) {
            throw new UnauthorisedException("This region already has a head assigned.");
        }

        User newUser = new User();
        newUser.setName(userDto.getName());
        newUser.setEmailId(userDto.getEmailId());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setContactNumber(userDto.getContactNumber());
        newUser.setRole(role);

        assignRegionToUser(newUser, role.getName(), region);

        sendDefaultPasswordEmail(newUser, role.getName());

        User savedUser = userRepo.save(newUser);
        setRegionHead(region, true);

        UserDto createdUser = new UserDto();
        createdUser.setId(savedUser.getId());
        createdUser.setName(savedUser.getName());
        createdUser.setEmailId(savedUser.getEmailId());
//        createdUser.setPassword(savedUser.getPassword());
        createdUser.setContactNumber(savedUser.getContactNumber());
        createdUser.setRole(savedUser.getRole().getName());
        createdUser.setRegionName(getRegionName(savedUser));
        return createdUser;
    }

    private boolean canCreateUser(Role currentUserRole, String requestedRole) {
        return switch (currentUserRole.getName().toUpperCase()) {
            case "SUPER_ADMIN" -> true;
            case "ADMIN" -> "STATE_HEAD".equalsIgnoreCase(requestedRole);
            case "STATE_HEAD" -> "DISTRICT_HEAD".equalsIgnoreCase(requestedRole);
            case "DISTRICT_HEAD" -> "TALUKA_HEAD".equalsIgnoreCase(requestedRole);
            case "TALUKA_HEAD" -> "DATA_COLLECTOR".equalsIgnoreCase(requestedRole);
            default -> throw new IllegalStateException("Invalid Role Name: " + currentUserRole.getName().toUpperCase());
        };
    }

    private boolean isUserHeadOfAnyRegion(String emailId) {
        return userRepo.existsByEmailIdAndIsHeadOfAnyRegion(emailId);
    }

    private Object fetchRegionForRole(String roleName, UserDto userDto) {
        return switch (roleName.toUpperCase()) {
            case "ADMIN" -> countryRepo.findByName(userDto.getRegionName())
                    .orElseThrow(() -> new MissingDataException("Invalid country name"));
            case "STATE_HEAD" -> stateRepo.findByName(userDto.getRegionName())
                    .orElseThrow(() -> new MissingDataException("Invalid state name"));
            case "DISTRICT_HEAD" -> districtRepo.findByName(userDto.getRegionName())
                    .orElseThrow(() -> new MissingDataException("Invalid district name"));
            case "TALUKA_HEAD" -> talukaRepo.findByName(userDto.getRegionName())
                    .orElseThrow(() -> new MissingDataException("Invalid taluka name"));
            case "DATA_COLLECTOR" -> cityRepo.findByName(userDto.getRegionName())
                    .orElseThrow(() -> new MissingDataException("Invalid city name"));
            default -> throw new IllegalStateException("Invalid Role Name: " + roleName.toUpperCase());
        };
    }

    private boolean regionAlreadyHasHead(Object region) {
        if (region instanceof Country) {
            return ((Country) region).isHead();
        } else if (region instanceof State) {
            return ((State) region).isHead();
        } else if (region instanceof District) {
            return ((District) region).isHead();
        } else if (region instanceof Taluka) {
            return ((Taluka) region).isHead();
        } else if (region instanceof City) {
            return ((City) region).isDataCollector();
        } else {
            throw new IllegalStateException("Unknown region type");
        }
    }

    private void setRegionHead(Object region, boolean head) {
        if (region instanceof Country) {
            ((Country) region).setHead(head);
            countryRepo.save((Country) region);
        } else if (region instanceof State) {
            ((State) region).setHead(head);
            stateRepo.save((State) region);
        } else if (region instanceof District) {
            ((District) region).setHead(head);
            districtRepo.save((District) region);
        } else if (region instanceof Taluka) {
            ((Taluka) region).setHead(head);
            talukaRepo.save((Taluka) region);
        } else if (region instanceof City) {
            ((City) region).setDataCollector(head);
            cityRepo.save((City) region);
        } else {
            throw new IllegalStateException("Unknown region type");
        }
    }

    private void assignRegionToUser(User user, String roleName, Object region) {
        switch (roleName.toUpperCase()) {
            case "ADMIN" -> user.setCountry((Country) region);
            case "STATE_HEAD" -> user.setState((State) region);
            case "DISTRICT_HEAD" -> user.setDistrict((District) region);
            case "TALUKA_HEAD" -> user.setTaluka((Taluka) region);
            case "DATA_COLLECTOR" -> user.setCity((City) region);
            default -> throw new IllegalStateException("Invalid Role Name: " + roleName.toUpperCase());
        }
    }

    private String getRegionName(User user) {
        if (user.getCountry() != null) {
            return user.getCountry().getName();
        } else if (user.getState() != null) {
            return user.getState().getName();
        } else if (user.getDistrict() != null) {
            return user.getDistrict().getName();
        } else if (user.getTaluka() != null) {
            return user.getTaluka().getName();
        } else if (user.getCity() != null) {
            return user.getCity().getName();
        } else {
            return "N/A";
        }
    }

    // Mail Send
    private void sendDefaultPasswordEmail(User user, String roleName) {
        try {
            String defaultPassword = "test123";
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

            helper.setTo(user.getEmailId());
            helper.setSubject("Welcome To HealthBizz");
            helper.setText(String.format(
                    "Welcome %s,\n\nYour account has been created!!\nYour Role is: %s.\nYour default password is: %s\n",
                    user.getName(), roleName, defaultPassword
            ));

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    // Get All Users
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();

        return users.stream()
                .map(userMapper::convertToDto)
                .collect(Collectors.toList());
    }


    //Delete User
    public void deleteUser(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepo.findByEmailId(authentication.getName()).orElseThrow();
        Role currentUserRole = currentUser.getRole();

        if (!canDeleteUser(currentUserRole)) {
            throw new UnauthorisedException("Unauthorized: You do not have permission to delete users.");
        }

        User userToDelete = userRepo.findById(userId).orElseThrow(() -> new MissingDataException("User not found"));

        if (isUserHeadOfAnyRegion(userToDelete.getEmailId())) {
            throw new UnauthorisedException("User cannot be deleted as they are head of a region.");
        }

        userRepo.delete(userToDelete);
    }

    private boolean canDeleteUser(Role currentUserRole) {
        return currentUserRole.getName().equalsIgnoreCase("SUPER_ADMIN");
    }

}
