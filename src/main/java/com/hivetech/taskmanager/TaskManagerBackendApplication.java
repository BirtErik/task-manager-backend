package com.hivetech.taskmanager;

import com.hivetech.taskmanager.role.model.Role;
import com.hivetech.taskmanager.role.repository.RoleRepository;
import com.hivetech.taskmanager.user.model.User;
import com.hivetech.taskmanager.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class TaskManagerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerBackendApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    CommandLineRunner run(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            Role adminRole = roleRepository.findByAuthority("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("Failed to get ADMIN role"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            Optional<User> user = userRepository.findByEmail("admin@admin.com");

            if (user.isEmpty()) {
                userRepository.save(new User(
                        "admin",
                        "admin@admin.com",
                        passwordEncoder.encode("password"),
                        roles
                ));
            }
        };
    }
}
