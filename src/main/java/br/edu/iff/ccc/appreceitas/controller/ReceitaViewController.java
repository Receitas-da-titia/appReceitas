package br.edu.iff.ccc.appreceitas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller responsável pelas páginas relacionadas às receitas.
 *
 * Mapeia as rotas relacionadas a:
 * - RF03 (Cadastro de Receitas)
 * - RF04 (Edição de Receitas)
 * - RF06 (Busca de Receitas)
 * - RF07 (Visualização de Receitas)
 * - RF11 (Organização por Categorias)
 *
 * Nesta fase apenas as rotas e as views são mapeadas. A lógica de
 * negócio (persistência, busca real, validações etc.) será implementada
 * em uma fase posterior.
 */
@Controller
public class ReceitaViewController {

    /**
     * Lista as receitas cadastradas.
     * Aceita filtros opcionais de busca (RF06), que serão tratados
     * na camada de serviço em uma fase futura.
     */
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

    /**
     * Exibe o formulário de cadastro de uma nova receita (RF03).
     */
    @GetMapping("/receitas/nova")
    public String novaReceita(Model model) {
        model.addAttribute("modoEdicao", false);
        return "receitas-formulario";
    }

    /**
     * Exibe os detalhes completos de uma receita (RF07).
     */
    @GetMapping("/receitas/{id}")
    public String detalhar(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "receitas-detalhe";
    }

    /**
     * Exibe o formulário de edição de uma receita já cadastrada (RF04).
     */
    @GetMapping("/receitas/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("modoEdicao", true);
        return "receitas-formulario";
    }
}
