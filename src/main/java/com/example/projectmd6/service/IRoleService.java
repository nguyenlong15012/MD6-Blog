package com.example.projectmd6.service;

import com.example.projectmd6.model.Role;
import com.example.projectmd6.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);
}
