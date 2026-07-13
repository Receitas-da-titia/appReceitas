package br.edu.iff.ccc.appreceitas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ReceitaViewController {

   // Lista as receitas cadastradas.

    @GetMapping("/receitas")
    public String listar(@RequestParam(name = "nome", required = false) String nome,
                          @RequestParam(name = "ingrediente", required = false) String ingrediente,
                          @RequestParam(name = "categoria", required = false) String categoria,
                          Model model) {
        model.addAttribute("nome", nome);
        model.addAttribute("ingrediente", ingrediente);
        model.addAttribute("categoria", categoria);
        return "receitas";
    }

     //Exibe o formulário de cadastro de uma nova receita 
    @GetMapping("/receitas/nova")
    public String novaReceita(Model model) {
        model.addAttribute("modoEdicao", false);
        return "receitas-formulario";
    }


    //Exibe os detalhes completos de uma receita
    @GetMapping("/receitas/{id}")
    public String detalhar(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "receitas-detalhe";
    }

    // Exibe o formulário de edição de uma receita já cadastrada 
    @GetMapping("/receitas/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("modoEdicao", true);
        return "receitas-formulario";
    }
}
