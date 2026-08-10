package br.edu.iff.ccc.appreceitas.controller;

import br.edu.iff.ccc.appreceitas.dto.ReceitaFormDTO;
import br.edu.iff.ccc.appreceitas.model.Receita;
import br.edu.iff.ccc.appreceitas.usecase.BuscarReceitaUseCase;
import br.edu.iff.ccc.appreceitas.usecase.CadastrarReceitaUseCase;
import br.edu.iff.ccc.appreceitas.usecase.ListarReceitasUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/receitas")
public class ReceitaViewController {

    private final CadastrarReceitaUseCase cadastrarReceitaUseCase;
    private final ListarReceitasUseCase listarReceitasUseCase;
    private final BuscarReceitaUseCase buscarReceitaUseCase;

    public ReceitaViewController(CadastrarReceitaUseCase cadastrarReceitaUseCase,
                                  ListarReceitasUseCase listarReceitasUseCase,
                                  BuscarReceitaUseCase buscarReceitaUseCase) {
        this.cadastrarReceitaUseCase = cadastrarReceitaUseCase;
        this.listarReceitasUseCase = listarReceitasUseCase;
        this.buscarReceitaUseCase = buscarReceitaUseCase;
    }

    // Lista as receitas cadastradas, com filtro opcional por nome/ingrediente/categoria.
    @GetMapping
    public String listar(@RequestParam(name = "nome", required = false) String nome,
                          @RequestParam(name = "ingrediente", required = false) String ingrediente,
                          @RequestParam(name = "categoria", required = false) String categoria,
                          Model model) {
        List<Receita> receitas = listarReceitasUseCase.executar(nome, ingrediente, categoria);

        model.addAttribute("receitas", receitas);
        model.addAttribute("nome", nome);
        model.addAttribute("ingrediente", ingrediente);
        model.addAttribute("categoria", categoria);
        return "receitas";
    }

    // Exibe o formulário de cadastro de uma nova receita.
    @GetMapping("/nova")
    public String novaReceita(Model model) {
        model.addAttribute("receita", new ReceitaFormDTO());
        model.addAttribute("modoEdicao", false);
        return "receitas-formulario";
    }

    // Recebe os dados do formulário, aciona o Use Case de cadastro e volta para a listagem.
    @PostMapping("/nova")
    public String cadastrar(@ModelAttribute("receita") ReceitaFormDTO dadosFormulario) {
        cadastrarReceitaUseCase.executar(dadosFormulario);
        return "redirect:/receitas";
    }

    // Exibe os detalhes completos de uma receita.
    @GetMapping("/{id}")
    public String detalhar(@PathVariable Long id, Model model) {
        Receita receita = buscarReceitaUseCase.executar(id)
                .orElseThrow(() -> new IllegalArgumentException("Receita não encontrada: " + id));
        model.addAttribute("receita", receita);
        return "receitas-detalhe";
    }

    // Exibe o formulário de edição de uma receita já cadastrada.
    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Receita receita = buscarReceitaUseCase.executar(id)
                .orElseThrow(() -> new IllegalArgumentException("Receita não encontrada: " + id));
        model.addAttribute("receita", receita);
        model.addAttribute("modoEdicao", true);
        return "receitas-formulario";
    }
}
