package com.example.DataSecurity.service;

import com.example.DataSecurity.model.Role;
import com.example.DataSecurity.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void initialize() {
        if (roleRepository.findByRoleName("ADMIN") == null) {
            Role admin = new Role();
            admin.setRoleName("ADMIN");
            save(admin);
        }

        if (roleRepository.findByRoleName("USER") == null) {
            Role user = new Role();
            user.setRoleName("USER");
            save(user);
        }
    }

    @Override
    public Role findById(int id) {
        return null;
    }

    @Override
    public Role findByName(String name) {
        return null;
    }
}
