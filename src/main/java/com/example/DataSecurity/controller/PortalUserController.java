package com.example.DataSecurity.controller;

import com.example.DataSecurity.model.PortalUser;
import com.example.DataSecurity.service.OrderService;
import com.example.DataSecurity.service.PortalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;
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
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
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

    @GetMapping("/admin/page")
    public String admin(Authentication auth) {
        /*Collection<?extends GrantedAuthority> authorities = auth.getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
            return "admin";
        }
        else {
            return "/home";
        }*/
        return "admin";
    }

    @GetMapping("/user")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public String user() {
        return "user";
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("portalUser", new PortalUser());
        return "/register";
    }

    @PostMapping("/register")
    public String register(@Valid PortalUser portalUser, BindingResult result){
        if (portalUser.getPortalUserPassword().length() < 8) {
            result.rejectValue("portalUserPassword", "test.test");
        }
        if (result.hasErrors()){
            return "register";
        }
        else {
            portalUserService.save(portalUser);
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public String details(Model model){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        PortalUser portalUser = portalUserService.findByLogin(user.getUsername());
        model.addAttribute("portalUser", portalUser);
        return "details";
    }

    @RequestMapping(value = "/details", method = RequestMethod.POST)
    public String update(@ModelAttribute(value = "portalUser") PortalUser portalUser, Model model) {

        PortalUser portalUserTemp = portalUserService.findById(portalUser.getPortalUserID());
        portalUserTemp.setPortalUserEmail(portalUser.getPortalUserEmail());

        portalUserService.update(portalUserTemp);

        model.addAttribute("portalUser", portalUserTemp);

        return "details";
    }
}
