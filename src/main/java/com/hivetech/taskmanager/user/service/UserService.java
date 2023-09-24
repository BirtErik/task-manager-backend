package com.hivetech.taskmanager.user.service;

import com.hivetech.taskmanager.user.dto.UserResponseDTO;
import com.hivetech.taskmanager.user.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    User loadByEmail(String email) throws UsernameNotFoundException;

    UserResponseDTO findById(long id);

    User save(User user);
}
