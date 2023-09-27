package com.hivetech.taskmanager.security.authentication.controller;

import com.hivetech.taskmanager.security.authentication.dto.LoginRequestDTO;
import com.hivetech.taskmanager.security.authentication.dto.LoginResponseDTO;
import com.hivetech.taskmanager.security.authentication.dto.UserRegisterDTO;
import com.hivetech.taskmanager.security.authentication.service.AuthenticationService;
import com.hivetech.taskmanager.user.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRegisterDTO userDTO) {
        return new ResponseEntity<>(authenticationService.registerUser(userDTO), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO loginDTO) {
        return new ResponseEntity<>(authenticationService.loginUser(loginDTO), HttpStatus.OK);
    }
}
