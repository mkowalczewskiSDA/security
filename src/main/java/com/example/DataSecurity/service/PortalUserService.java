package com.example.DataSecurity.service;

import com.example.DataSecurity.model.PortalUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PortalUserService {
    PortalUser findById(int id);
    PortalUser findByLogin(String login);
    List<PortalUser> findByEmail(String email);
    List<PortalUser> findByLoginStartsWith(String login);
    Page<PortalUser> findAllPaginated(Pageable pageable);

    public void update(PortalUser portalUser);

    public void save(PortalUser portalUser);


}
