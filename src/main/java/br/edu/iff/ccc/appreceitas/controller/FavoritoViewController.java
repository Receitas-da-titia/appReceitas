package br.edu.iff.ccc.appreceitas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class FavoritoViewController {

    @GetMapping("/favoritos")
    public String listar() {
        return "favoritos";
    }
}
