package com.project.LibraryManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.LibraryManagementSystem.model.User;
import com.project.LibraryManagementSystem.model.UserPrincipal;
import com.project.LibraryManagementSystem.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService 
{
    @Autowired
    private UserRepository userRepo;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        User user = userRepo.findByEmail(email);

        if(user == null)
        {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        //
        return new UserPrincipal(user);
    }
}
