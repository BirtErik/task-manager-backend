package com.hivetech.taskmanager.user.service;

import com.hivetech.taskmanager.common.exception.CustomException;
import com.hivetech.taskmanager.user.dto.UserResponseDTO;
import com.hivetech.taskmanager.user.model.User;
import com.hivetech.taskmanager.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponseDTO findById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new CustomException("User with id " + id + " not found", HttpStatus.NOT_FOUND));

        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws CustomException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("Email is not valid", HttpStatus.CONFLICT));

        return user;
    }
}
