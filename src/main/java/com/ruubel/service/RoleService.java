package com.ruubel.service;

import com.ruubel.model.Role;
import com.ruubel.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByRole(String roleStr) {
        return roleRepository.findByRole(roleStr);
    }

    public Role saveRole(String role) {
        Role roleObj = new Role(role);
        roleRepository.save(roleObj);
        return roleObj;
    }
}
