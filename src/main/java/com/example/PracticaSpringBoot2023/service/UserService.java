package com.example.PracticaSpringBoot2023.service;

import com.example.PracticaSpringBoot2023.dto.UserDto;
import com.example.PracticaSpringBoot2023.model.Role;
import com.example.PracticaSpringBoot2023.model.User;
import com.example.PracticaSpringBoot2023.repository.RoleRepository;
import com.example.PracticaSpringBoot2023.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(UserDto userDto) {
        Role role = roleRepository.findByName(userDto.getRole());

        if (role == null) {
            role = roleRepository.save(new Role(userDto.getRole()));
        }

        User user = new User(userDto.getName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
                Arrays.asList(role));
        userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}