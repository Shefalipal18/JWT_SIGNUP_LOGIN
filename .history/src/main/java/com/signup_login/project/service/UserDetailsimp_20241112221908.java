package com.signup_login.project.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.signup_login.project.repository.UserRepository;

public class UserDetailsImp implements UserDetailsService {

    private final UserRepository repository;

  

    public UserDetailsImp(UserRepository repository) {
        this.repository = repository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    
}