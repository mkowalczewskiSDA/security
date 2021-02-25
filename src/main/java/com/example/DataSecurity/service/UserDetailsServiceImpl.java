package com.example.DataSecurity.service;

import com.example.DataSecurity.model.PortalUser;
import com.example.DataSecurity.model.Role;
import com.example.DataSecurity.repository.PortalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PortalUserRepository portalUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PortalUser portalUser = portalUserRepository.findByPortalUserLogin(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if(portalUser != null)
            for (Role role : portalUser.getRoles()){
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
        else{
            throw new UsernameNotFoundException(username + " is not found");
        }
        portalUser.getRoles().forEach(role -> System.out.println(role.getRoleName()));
        return new User(portalUser.getPortalUserLogin(), portalUser.getPortalUserPassword(), grantedAuthorities);
    }
}
