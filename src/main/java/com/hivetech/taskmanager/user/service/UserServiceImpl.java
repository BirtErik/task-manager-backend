package com.hivetech.taskmanager.user.service;

import com.hivetech.taskmanager.common.exception.CustomException;
import com.hivetech.taskmanager.user.dto.UserResponseDTO;
import com.hivetech.taskmanager.user.model.User;
import com.hivetech.taskmanager.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new CustomException("Email not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public UserResponseDTO findById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new CustomException("User with id " + id + " not found", HttpStatus.NOT_FOUND));

        return UserResponseDTO.fromEntity(user);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
}
