package br.edu.iff.ccc.appreceitas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


//Controller responsável pela página inicial da aplicação.

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("tituloPagina", "Receitas da Titia");
        model.addAttribute("totalCategorias", 0);
        model.addAttribute("destaque", "Confira nossas receitas");
        return "home";
    }
}
