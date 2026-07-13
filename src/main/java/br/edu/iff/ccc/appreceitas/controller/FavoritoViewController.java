package br.edu.iff.ccc.appreceitas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller responsável pela área logada de favoritos do usuário.
 *
 * Mapeia as rotas relacionadas a:
 * - RF08 (Sistema de Favoritos)
 * - RF09 (Remoção de Favoritos)
 * - RF10 (Área Logada)
 *
 * Nesta fase apenas a rota e a view são mapeadas. O controle de sessão,
 * autenticação e a lógica de negócio serão implementados em uma fase
 * posterior.
 */
@Controller
public class FavoritoViewController {

    @GetMapping("/favoritos")
    public String listar() {
        return "favoritos";
    }
}
