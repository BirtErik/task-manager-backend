package com.hivetech.taskmanager.security.authentication.service;

import com.hivetech.taskmanager.common.exception.CustomException;
import com.hivetech.taskmanager.role.model.Role;
import com.hivetech.taskmanager.role.repository.RoleRepository;
import com.hivetech.taskmanager.security.authentication.dto.LoginRequestDTO;
import com.hivetech.taskmanager.security.authentication.dto.LoginResponseDTO;
import com.hivetech.taskmanager.security.authentication.dto.UserRegisterDTO;
import com.hivetech.taskmanager.user.dto.UserResponseDTO;
import com.hivetech.taskmanager.user.model.User;
import com.hivetech.taskmanager.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    private final AuthenticationManager authManager;

    private final TokenService tokenService;

    @Autowired
    public AuthenticationServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            ModelMapper modelMapper, AuthenticationManager authManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @Override
    @Transactional
    public UserResponseDTO registerUser(UserRegisterDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new CustomException("User with email: " + userDTO.getEmail() + "already exists", HttpStatus.CONFLICT);
        }

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        Role userRole = roleRepository.findByAuthority("ROLE_USER").get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);

        return modelMapper.map(userRepository.save(
                new User(
                        userDTO.getUsername(),
                        userDTO.getEmail(),
                        encodedPassword,
                        authorities
                )
        ), UserResponseDTO.class);
    }

    @Override
    public LoginResponseDTO loginUser(LoginRequestDTO loginDTO) {
        //Request comes into auth manager who checks for username(email) and password in db
        //If the user exist it creates the Authentication token which is sent to TokenService
        //Token service generates JWT
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );
        String token = tokenService.generateJwt(auth);

        return new LoginResponseDTO(modelMapper.map(
                userRepository.findByEmail(loginDTO.getEmail()),
                UserResponseDTO.class), token
        );
    }

}



