package com.project.LibraryManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.LibraryManagementSystem.model.User;
import com.project.LibraryManagementSystem.repository.UserRepository;

@Service
public class UserService 
{
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public void saveUser(User user) 
    {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public String verifyUser(User user) throws UsernameNotFoundException
    {
        Authentication authentication = authManager
                                        .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        
        if(authentication.isAuthenticated())                                
        {
            return jwtService.generateToken(user.getEmail());
        }
        //
        return "Failed";
    }
}