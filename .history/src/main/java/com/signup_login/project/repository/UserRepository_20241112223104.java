package com.signup_login.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.signup_login.project.model.User;


public interface UserRepository extends JpaRepository<User, Integer>{

    Optional<User> findByUsername(String username);
    
    
}
