package com.example.projectmd6.service.impl;

import com.example.projectmd6.model.Role;
import com.example.projectmd6.model.RoleName;
import com.example.projectmd6.repository.IRoleRepository;
import com.example.projectmd6.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceIpml implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
