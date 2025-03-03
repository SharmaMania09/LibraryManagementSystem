package com.project.LibraryManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.LibraryManagementSystem.model.User;
import com.project.LibraryManagementSystem.repository.UserRepository;

@Service
public class UserService 
{
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public void saveUser(User user) 
    {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}