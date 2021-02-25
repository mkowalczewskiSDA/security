package com.example.DataSecurity.repository;

import com.example.DataSecurity.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Page<Order> findAll(Pageable pageable);

}
