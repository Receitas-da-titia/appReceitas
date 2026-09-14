package br.edu.iff.ccc.appreceitas.controller;

import br.edu.iff.ccc.appreceitas.service.CategoriaService;
import br.edu.iff.ccc.appreceitas.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ReceitaService receitaService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("tituloPagina", "Receitas da Titia");
        model.addAttribute("totalCategorias", categoriaService.listarTodas().size());
        model.addAttribute("destaque", "Confira nossas receitas");
        model.addAttribute("categorias", categoriaService.listarTodas());
        model.addAttribute("receitas", receitaService.listarTodas().stream().limit(3).toList());
        return "home";
    }
}
