package com.example.DataSecurity.repository;

import com.example.DataSecurity.model.PortalUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//https://www.baeldung.com/spring-data-derived-queries
//https://www.baeldung.com/spring-jpa-like-queries
//https://www.baeldung.com/spring-data-jpa-query

@Repository
public interface PortalUserRepository extends JpaRepository<PortalUser, Integer> {

    PortalUser findByPortalUserID(int id);

    List<PortalUser> findByPortalUserEmail(String email);

    PortalUser findByPortalUserLogin(String login);

    List<PortalUser> findByPortalUserLoginStartsWith(String login);

    Page<PortalUser> findAll(Pageable pageable);

}
