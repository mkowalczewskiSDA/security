package com.example.DataSecurity.service;

import com.example.DataSecurity.model.PortalUser;
import com.example.DataSecurity.repository.PortalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortalUserServiceImpl implements PortalUserService {

    @Autowired
    PortalUserRepository portalUserRepository;

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



}
