package br.edu.iff.ccc.appreceitas.controller;

import br.edu.iff.ccc.appreceitas.dto.ComentarioDTO;
import br.edu.iff.ccc.appreceitas.dto.ReceitaDTO;
import br.edu.iff.ccc.appreceitas.model.Ingrediente;
import br.edu.iff.ccc.appreceitas.model.Receita;
import br.edu.iff.ccc.appreceitas.service.CategoriaService;
import br.edu.iff.ccc.appreceitas.service.ComentarioService;
import br.edu.iff.ccc.appreceitas.service.FavoritoService;
import br.edu.iff.ccc.appreceitas.service.IngredienteService;
import br.edu.iff.ccc.appreceitas.service.ReceitaService;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReceitaViewController {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private FavoritoService favoritoService;

    @GetMapping("/receitas")
    public String listar(@RequestParam(name = "nome", required = false) String nome,
                          @RequestParam(name = "idCategoria", required = false) Long idCategoria,
                          @RequestParam(name = "idIngrediente", required = false) Long idIngrediente,
                          Model model) {
        model.addAttribute("receitas", receitaService.buscar(nome, idCategoria, idIngrediente));
        model.addAttribute("categorias", categoriaService.listarTodas());
        model.addAttribute("ingredientes", ingredienteService.listarTodos());
        model.addAttribute("nome", nome);
        model.addAttribute("idCategoria", idCategoria);
        model.addAttribute("idIngrediente", idIngrediente);
        return "receitas";
    }

    @GetMapping("/receitas/nova")
    public String novaReceita(Model model) {
        model.addAttribute("receitaDTO", new ReceitaDTO());
        model.addAttribute("categorias", categoriaService.listarTodas());
        model.addAttribute("ingredientes", ingredienteService.listarTodos());
        model.addAttribute("modoEdicao", false);
        return "receitas-formulario";
    }

    @PostMapping("/receitas/nova")
    public String salvarNovaReceita(@ModelAttribute ReceitaDTO receitaDTO) {
        Receita receita = receitaService.cadastrar(receitaDTO);
        return "redirect:/receitas/" + receita.getIdReceita();
    }

    @GetMapping("/receitas/{id}")
    public String detalhar(@PathVariable Long id, HttpSession session, Model model) {
    Receita receita = receitaService.buscarPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("Receita não encontrada"));
    
    model.addAttribute("receita", receita);
    
    if (receita.getIdCategoria() != null) {
        model.addAttribute("categoria", categoriaService.buscarPorId(receita.getIdCategoria()).orElse(null));
    } else {
        model.addAttribute("categoria", null);
    }

    List<Ingrediente> ingredientesReceita = List.of();
    if (receita.getIdsIngredientes() != null && !receita.getIdsIngredientes().isEmpty()) {
        ingredientesReceita = ingredienteService.listarTodos().stream()
                .filter(i -> receita.getIdsIngredientes().contains(i.getIdIngrediente()))
                .toList();
    }
    model.addAttribute("ingredientes", ingredientesReceita);
    
    model.addAttribute("comentarios", comentarioService.listarPorReceita(id));
    model.addAttribute("comentarioDTO", new ComentarioDTO());
    model.addAttribute("usuarioLogadoId", session.getAttribute("usuarioLogadoId"));
    
    return "receitas-detalhe";
}


    @GetMapping("/receitas/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Receita receita = receitaService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Receita não encontrada"));
        ReceitaDTO dto = new ReceitaDTO();
        dto.setNome(receita.getNome());
        dto.setModoPreparo(receita.getModoPreparo());
        dto.setTempoPreparo(receita.getTempoPreparo());
        dto.setImagem(receita.getImagem());
        dto.setIdCategoria(receita.getIdCategoria());
        dto.setIdsIngredientes(receita.getIdsIngredientes());
        model.addAttribute("receitaDTO", dto);
        model.addAttribute("id", id);
        model.addAttribute("categorias", categoriaService.listarTodas());
        model.addAttribute("ingredientes", ingredienteService.listarTodos());
        model.addAttribute("modoEdicao", true);
        return "receitas-formulario";
    }

    @PostMapping("/receitas/{id}/editar")
    public String salvarEdicao(@PathVariable Long id, @ModelAttribute ReceitaDTO receitaDTO) {
        receitaService.atualizar(id, receitaDTO);
        return "redirect:/receitas/" + id;
    }

    @PostMapping("/receitas/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        receitaService.excluir(id);
        return "redirect:/receitas";
    }

    @PostMapping("/receitas/{id}/favoritar")
    public String favoritar(@PathVariable Long id, HttpSession session) {
        Long idUsuario = (Long) session.getAttribute("usuarioLogadoId");
        if (idUsuario == null) {
            return "redirect:/login";
        }
        favoritoService.adicionar(idUsuario, id);
        return "redirect:/receitas/" + id;
    }

    @PostMapping("/receitas/{id}/comentarios")
    public String comentar(@PathVariable Long id, @ModelAttribute ComentarioDTO comentarioDTO, HttpSession session) {
        Long idUsuario = (Long) session.getAttribute("usuarioLogadoId");
        if (idUsuario == null) {
            return "redirect:/login";
        }
        comentarioService.adicionar(idUsuario, id, comentarioDTO);
        return "redirect:/receitas/" + id;
    }

    @PostMapping("/comentarios/{id}/excluir")
    public String excluirComentario(@PathVariable Long id, @RequestParam Long idReceita) {
        comentarioService.excluir(id);
        return "redirect:/receitas/" + idReceita;
    }
}
