package br.edu.iff.ccc.appreceitas.controller;

import br.edu.iff.ccc.appreceitas.dto.LoginDTO;
import br.edu.iff.ccc.appreceitas.dto.UsuarioDTO;
import br.edu.iff.ccc.appreceitas.model.Usuario;
import br.edu.iff.ccc.appreceitas.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Optional;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;


import java.util.Optional;

@Controller
public class AutenticacaoViewController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "login";
    }

    @PostMapping("/login")
    public String autenticar(@Valid @ModelAttribute("loginDTO") LoginDTO loginDTO, BindingResult bindingResult, HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        Optional<Usuario> usuario = usuarioService.autenticar(loginDTO);
        if (usuario.isEmpty()) {
            model.addAttribute("erro", "Email ou senha inválidos");
            return "login";
        }
        session.setAttribute("usuarioLogadoId", usuario.get().getIdUsuario());
        session.setAttribute("usuarioLogadoNome", usuario.get().getNome());
        return "redirect:/home";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(@Valid @ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
        return "cadastro";
    }
    usuarioService.cadastrar(usuarioDTO);
    return "redirect:/login";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
