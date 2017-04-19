package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Chatzakis Nikolaos
 */
@Controller
public class LoginController {

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
}
