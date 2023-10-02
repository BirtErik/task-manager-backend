package com.hivetech.taskmanager.security.authentication.dto;

import com.hivetech.taskmanager.user.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    UserResponseDTO data;
    private String jwt;
}
