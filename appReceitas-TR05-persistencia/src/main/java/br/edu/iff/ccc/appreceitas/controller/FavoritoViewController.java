package br.edu.iff.ccc.appreceitas.controller;

import br.edu.iff.ccc.appreceitas.model.Favorito;
import br.edu.iff.ccc.appreceitas.model.Receita;
import br.edu.iff.ccc.appreceitas.service.FavoritoService;
import br.edu.iff.ccc.appreceitas.service.ReceitaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FavoritoViewController {

    @Autowired
    private FavoritoService favoritoService;

    @Autowired
    private ReceitaService receitaService;

    @GetMapping("/favoritos")
    public String listar(HttpSession session, Model model) {
        Long idUsuario = (Long) session.getAttribute("usuarioLogadoId");
        if (idUsuario == null) {
            return "redirect:/login";
        }
        List<Favorito> favoritos = favoritoService.listarPorUsuario(idUsuario);
        Map<Favorito, Receita> favoritosComReceita = new LinkedHashMap<>();
        for (Favorito favorito : favoritos) {
            receitaService.buscarPorId(favorito.getIdReceita())
                    .ifPresent(receita -> favoritosComReceita.put(favorito, receita));
        }
        model.addAttribute("favoritosComReceita", favoritosComReceita);
        return "favoritos";
    }

    @PostMapping("/favoritos/{id}/remover")
    public String remover(@PathVariable Long id) {
        favoritoService.remover(id);
        return "redirect:/favoritos";
    }
}
