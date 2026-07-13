package br.edu.iff.ccc.appreceita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homecontroller {
    
    @GetMapping("/home")
    public String home() {
        // Indica ao Thymeleaf para buscar o arquivo "home.html" em resources/templates
        return "home"; 
    }
}