package com.hivetech.taskmanager.user.service;

import com.hivetech.taskmanager.user.dto.UserResponseDTO;
import com.hivetech.taskmanager.user.model.User;
import com.hivetech.taskmanager.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
    }

    @Override
    public UserResponseDTO findById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User with id " + id + " not found"));

        return UserResponseDTO.fromEntity(user);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
}
