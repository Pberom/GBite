package com.example.GBite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GBiteControllers {
    @GetMapping("/")
    public String greeting(Model model) {
        return "home";
    }
}
