package br.edu.iff.ccc.appreceitas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller responsável pelas páginas de autenticação e cadastro de usuários.
 *
 * Mapeia as rotas relacionadas a:
 * - RF01 (Cadastro de Usuários)
 * - RF02 (Login de Usuários)
 * - RF13 (Logout do Sistema)
 *
 * Nesta fase apenas as rotas e as views são mapeadas. A lógica de
 * autenticação, validação e persistência será implementada em uma
 * fase posterior.
 */
@Controller
public class AutenticacaoViewController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
