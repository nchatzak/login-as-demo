package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Chatzakis Nikolaos
 */
@Controller
@RequestMapping("/mod")
public class ModeratorController {

    @GetMapping
    public String toMod() {
        return "moderator";
    }
}
