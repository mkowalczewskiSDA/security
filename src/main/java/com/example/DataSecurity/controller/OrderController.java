package com.example.DataSecurity.controller;

import com.example.DataSecurity.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/list")
    public String getOrders(Model model) {
        //orderService.getAllOrders().forEach(System.out::println);
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }

}
