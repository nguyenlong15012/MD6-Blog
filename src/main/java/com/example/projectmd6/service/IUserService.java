package com.example.projectmd6.service;

import com.example.projectmd6.model.Users;

import java.util.Optional;

public interface IUserService {
    Optional<Users> findByUsername(String name); // Tim kiem user co ton tai trong DB kh?
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Users save(Users users);
    Optional<Users> findById(Long id);
}
