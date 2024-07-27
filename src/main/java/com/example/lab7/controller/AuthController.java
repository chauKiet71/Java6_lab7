package com.example.lab7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @RequestMapping("/auth/login/form")
    public String form() {
        return "auth/login";
    }

    @RequestMapping("/auth/login/success")
    public String success(Model model) {
        model.addAttribute("message", "Đăng nhập thành công");
        return "forward:/auth/login/form";
    }

    @RequestMapping("/auth/login/error")
    public String error(Model model) {
        model.addAttribute("message", "Sai thông tin đăng nhập!");
        return "forward:/auth/login/form";
    }

    @RequestMapping("/auth/loggout/success")
    public String logoff(Model model) {
        model.addAttribute("message", "Đăng xuất thành công!");
        return "forward:/auth/login/form";
    }

    @RequestMapping("/auth/access/denied")
    public String denied(Model model) {
        model.addAttribute("message", "Bạn không có quyền truy cập!");
        return "auth/login";
    }
}
