package com.example.DataSecurity.repository;

import com.example.DataSecurity.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAll();

}
