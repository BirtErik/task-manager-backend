package com.hivetech.taskmanager.security.authentication.service;

import org.springframework.security.core.Authentication;

public interface TokenService {
    String generateJwt(Authentication auth);
}
