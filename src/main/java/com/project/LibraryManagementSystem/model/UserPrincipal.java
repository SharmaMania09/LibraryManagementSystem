package com.project.LibraryManagementSystem.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails 
{
    private User user;

    public UserPrincipal(User user)
    {
        this.user = user;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() 
    {
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
        // throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    }

    @Override
    public String getPassword() 
    {
        return user.getPassword();
        // throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }

    @Override
    public String getUsername() 
    {
        return user.getEmail();
        // throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Modify this if you have an expiration policy
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Modify this if you have a locking mechanism
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modify this if credentials expire
    }

    @Override
    public boolean isEnabled() {
        return true; // Modify this based on user activation status
    }
    
}
