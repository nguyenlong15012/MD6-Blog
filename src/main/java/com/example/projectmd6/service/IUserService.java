package com.example.projectmd6.service;

import com.example.projectmd6.model.Post;
import com.example.projectmd6.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserService {
    Optional<Users> findByUsername(String name); // Tim kiem user co ton tai trong DB kh?
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Users save(Users users);
    void remove(Long id);
    Optional<Users> findById(Long id);
//    Iterable<Users> findAll(Pageable pageable);
    Page<Users> findAll(Pageable pageable);
}
