package com.hivetech.taskmanager.user.service;

import com.hivetech.taskmanager.user.dto.UserResponseDTO;
import com.hivetech.taskmanager.user.model.User;

public interface UserService {

    UserResponseDTO findById(long id);

    User save(User user);
}
