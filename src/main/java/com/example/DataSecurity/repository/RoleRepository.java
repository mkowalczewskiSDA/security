package com.example.DataSecurity.repository;

import com.example.DataSecurity.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleId(int id);
    Role findByRoleName(String name);
    List<Role> findByRoleNameContains(String name);

}

