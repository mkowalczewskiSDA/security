package com.example.DataSecurity.controller;

import com.example.DataSecurity.model.PortalUser;
import com.example.DataSecurity.service.OrderService;
import com.example.DataSecurity.service.PortalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class PortalUserController {

    @Autowired
    private PortalUserService portalUserService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/users")
    public String usersList(Model model,
                            @RequestParam("page") Optional<Integer> page,
                            @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int currentSize = size.orElse(5);

        Page<PortalUser> portalUserPage = portalUserService.findAllPaginated(PageRequest.of(currentPage - 1, currentSize));

        model.addAttribute("size", currentSize);
        model.addAttribute("portalUserPage", portalUserPage);

        int totalPages = portalUserPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "/users";
    }
}
