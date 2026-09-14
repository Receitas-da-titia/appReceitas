package br.edu.iff.ccc.appreceitas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Controller responsável pelas páginas de autenticação e cadastro de usuários
@Controller
public class AutenticacaoViewController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
