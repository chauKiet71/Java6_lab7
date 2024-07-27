package com.example.lab7.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    HttpServletRequest req;

    @RequestMapping("/home/index")
    public String index(Model model) {
        model.addAttribute("message", "This is home page");
        return "home/index";
    }

    @RequestMapping("/home/about")
    public String about(Model model) {
        model.addAttribute("message", "This is introduce page");
        return "home/index";
    }

    @ModelAttribute("remoteUser")
    public String getRemoteUser() {
        return req.getRemoteUser();
    }

    @ModelAttribute("isAdmin")
    public boolean isAdmin() {
        return req.isUserInRole("ADMIN");
    }

    @ModelAttribute("userPrincipal")
    public Principal getUserPrincipal() {
        return req.getUserPrincipal();
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/home/admins")
    public String admins(Model model) {
        if (!req.isUserInRole("ADMIN")) {
            return "redirect:/auth/access/denied";
        }
        model.addAttribute("message", "Hello administrator");
        return "home/index";
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @RequestMapping("/home/users")
    public String users(Model model) {
        if (!(req.isUserInRole("ADMIN") || req.isUserInRole("USER"))) {
            return "redirect:/auth/access/denied";
        }
        model.addAttribute("message", "Hello users");
        return "home/index";
    }

//    @PreAuthorize("isAuthenticated()")//phải đăng nhapaj thì mới được phép truy cập
    @RequestMapping("/home/authenticated")
    public String authenticated(Model model) {
        if (req.getRemoteUser() == null) {
            return "redirect:/auth/login/form";
        }
        model.addAttribute("message", "Hello authenticated");
        return "home/index";
    }

    @RequestMapping("/home/trymeleaf1")
    public String trymeleaf1(Model model) {
        model.addAttribute("message", "Hello trymeleaf1");
        return "home/thymeleaf1";
    }

    @RequestMapping("/home/thymeleaf2")
    public String thymeleaf2(Model model) {
        model.addAttribute("message", "Hello thymeleaf2");
        return "home/thymeleaf2";
    }
}
