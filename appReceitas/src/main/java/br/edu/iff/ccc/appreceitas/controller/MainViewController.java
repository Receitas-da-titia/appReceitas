package br.edu.iff.ccc.webproject.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String inicio(Model model) {

        model.addAttribute("tituloPagina", "DevTech Store");
        model.addAttribute("totalCategorias", 5);
        model.addAttribute("destaque", "Produtos em promoção");

        return "inicial";
    }
}

