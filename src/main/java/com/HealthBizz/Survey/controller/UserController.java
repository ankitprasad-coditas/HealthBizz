package com.HealthBizz.Survey.controller;

import com.HealthBizz.Survey.dto.ApiResponseDto;
import com.HealthBizz.Survey.dto.AuthRequestDto;
import com.HealthBizz.Survey.dto.TokenResponseDto;
import com.HealthBizz.Survey.dto.UserDto;
import com.HealthBizz.Survey.service.AuthService;
import com.HealthBizz.Survey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {

    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public UserController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    // User Login
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<TokenResponseDto>> userLogin(@RequestBody AuthRequestDto authRequestDto) {
        ApiResponseDto<TokenResponseDto> response = new ApiResponseDto<>(authService.login(authRequestDto), HttpStatus.OK.value(), "Logged In Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Create User
    @PostMapping("/createUser")
    public ResponseEntity<ApiResponseDto<UserDto>> createUser(@RequestBody UserDto userDto) {
        ApiResponseDto<UserDto> response = new ApiResponseDto<>(userService.createUser(userDto), HttpStatus.CREATED.value(), "User Created Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get user by ID
    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }


}
