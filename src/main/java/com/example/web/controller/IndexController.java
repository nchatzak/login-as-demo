package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Chatzakis Nikolaos
 */
@Controller
public class IndexController {

    @GetMapping(value = {"/", "/index"})
    public String toIndex() {
        return "index";
    }
}
