package com.hivetech.taskmanager.security.authentication.service;

import com.hivetech.taskmanager.security.authentication.dto.LoginRequestDTO;
import com.hivetech.taskmanager.security.authentication.dto.LoginResponseDTO;
import com.hivetech.taskmanager.security.authentication.dto.UserRegisterDTO;
import com.hivetech.taskmanager.user.dto.UserResponseDTO;

public interface AuthenticationService {
    UserResponseDTO registerUser(UserRegisterDTO userDTO);

    LoginResponseDTO loginUser(LoginRequestDTO loginDTO);
}
