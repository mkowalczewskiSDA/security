package com.example.DataSecurity.controller;

import com.example.DataSecurity.model.Order;
import com.example.DataSecurity.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/list")
    public String getOrders(Model model,
                            @RequestParam("page") Optional<Integer> page){
        int currentPage = page.orElse(1);

        Page<Order> orderPage = orderService.getAllOrdersPaginated(PageRequest.of(currentPage - 1, 10));

        model.addAttribute("orderPage", orderPage);

        int totalPages = orderPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            /* to jest to samo tylko w pętli for a nie w streamie
            List<Integer> pageNumbersFor = new ArrayList<>();
            for (int i = 1; i<=totalPages; i++){
                pageNumbersFor.add(i);
            }*/
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "orders";
    }

}
