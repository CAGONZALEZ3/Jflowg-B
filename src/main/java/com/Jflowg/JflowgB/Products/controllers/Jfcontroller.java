package com.Jflowg.JflowgB.Products.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class Jfcontroller {
    
    @PostMapping(value = "login")
    public String login() {
        return "hola soy camilo";
    }
}
