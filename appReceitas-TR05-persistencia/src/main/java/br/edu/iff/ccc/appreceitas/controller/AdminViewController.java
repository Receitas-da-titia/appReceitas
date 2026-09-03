package br.edu.iff.ccc.appreceitas.controller;

import br.edu.iff.ccc.appreceitas.dto.CategoriaDTO;
import br.edu.iff.ccc.appreceitas.dto.IngredienteDTO;
import br.edu.iff.ccc.appreceitas.service.CategoriaService;
import br.edu.iff.ccc.appreceitas.service.IngredienteService;
import br.edu.iff.ccc.appreceitas.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class AdminViewController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private ReceitaService receitaService;

    @GetMapping("/admin")
    public String painel(Model model) {
        model.addAttribute("totalReceitas", receitaService.listarTodas().size());
        model.addAttribute("totalCategorias", categoriaService.listarTodas().size());
        model.addAttribute("totalIngredientes", ingredienteService.listarTodos().size());
        return "admin";
    }

    @GetMapping("/admin/categorias")
    public String categorias(Model model) {
        model.addAttribute("categorias", categoriaService.listarTodas());
        model.addAttribute("categoriaDTO", new CategoriaDTO());
        return "admin-categorias";
    }

    @PostMapping("/admin/categorias")
    public String salvarCategoria(@Valid @ModelAttribute("categoriaDTO") CategoriaDTO categoriaDTO, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listarTodas());
            return "admin-categorias";
        }
        categoriaService.cadastrar(categoriaDTO);
        return "redirect:/admin/categorias";
    }

    @PostMapping("/admin/categorias/{id}/editar")
    public String editarCategoria(@PathVariable Long id, @Valid @ModelAttribute("categoriaDTO") CategoriaDTO categoriaDTO, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listarTodas());
            return "admin-categorias";
        }
        categoriaService.atualizar(id, categoriaDTO);
        return "redirect:/admin/categorias";
    }

    @PostMapping("/admin/categorias/{id}/excluir")
    public String excluirCategoria(@PathVariable Long id) {
        categoriaService.excluir(id);
        return "redirect:/admin/categorias";
    }

    @GetMapping("/admin/ingredientes")
    public String ingredientes(Model model) {
        model.addAttribute("ingredientes", ingredienteService.listarTodos());
        model.addAttribute("ingredienteDTO", new IngredienteDTO());
        return "admin-ingredientes";
    }

    @PostMapping("/admin/ingredientes")
    public String salvarIngrediente(@Valid @ModelAttribute("ingredienteDTO") IngredienteDTO ingredienteDTO, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("ingredientes", ingredienteService.listarTodos());
            return "admin-ingredientes";
        }
        ingredienteService.cadastrar(ingredienteDTO);
        return "redirect:/admin/ingredientes";
    }

    @PostMapping("/admin/ingredientes/{id}/editar")
    public String editarIngrediente(@PathVariable Long id, @Valid @ModelAttribute("ingredienteDTO") IngredienteDTO ingredienteDTO, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("ingredientes", ingredienteService.listarTodos());
            return "admin-ingredientes";
        }
        ingredienteService.atualizar(id, ingredienteDTO);
        return "redirect:/admin/ingredientes";
    }

    @PostMapping("/admin/ingredientes/{id}/excluir")
    public String excluirIngrediente(@PathVariable Long id) {
        ingredienteService.excluir(id);
        return "redirect:/admin/ingredientes";
    }

    @PostMapping("/admin/receitas/{id}/excluir")
    public String excluirReceita(@PathVariable Long id) {
        receitaService.excluir(id);
        return "redirect:/admin";
    }
}
