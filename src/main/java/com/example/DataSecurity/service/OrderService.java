package com.example.DataSecurity.service;

import com.example.DataSecurity.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Page<Order> getAllOrdersPaginated(Pageable pageable);

}
