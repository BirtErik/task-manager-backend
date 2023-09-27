package com.hivetech.taskmanager.security.authentication.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDTO {
    private String username;
    private String email;
    private String password;
}
