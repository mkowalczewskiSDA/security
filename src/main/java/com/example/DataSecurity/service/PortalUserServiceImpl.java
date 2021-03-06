package com.example.DataSecurity.service;

import com.example.DataSecurity.model.PortalUser;
import com.example.DataSecurity.model.Role;
import com.example.DataSecurity.repository.PortalUserRepository;
import com.example.DataSecurity.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PortalUserServiceImpl implements PortalUserService {

    @Autowired
    PortalUserRepository portalUserRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public PortalUser findById(int id) {
        return portalUserRepository.findByPortalUserID(id);
    }

    @Override
    public PortalUser findByLogin(String login) {
        return portalUserRepository.findByPortalUserLogin(login);
    }

    @Override
    public List<PortalUser> findByEmail(String email) {
        return portalUserRepository.findByPortalUserEmail(email);
    }

    @Override
    public List<PortalUser> findByLoginStartsWith(String login) {
        return portalUserRepository.findByPortalUserLoginStartsWith(login);
    }

    @Override
    public Page<PortalUser> findAllPaginated(Pageable pageable) {
        return portalUserRepository.findAll(pageable);
    }

    @Override
    public void update(PortalUser portalUser){
        portalUserRepository.save(portalUser);
    }

    @Override
    public void save(PortalUser portalUser) {
        portalUser.setPortalUserPassword(passwordEncoder.encode(portalUser.getPortalUserPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByRoleName("USER"));
        portalUser.setRoles(roles);
        portalUserRepository.save(portalUser);
    }



}
