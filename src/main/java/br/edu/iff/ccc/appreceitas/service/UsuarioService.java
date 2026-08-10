package br.edu.iff.ccc.appreceitas.service;

import br.edu.iff.ccc.appreceitas.dto.LoginDTO;
import br.edu.iff.ccc.appreceitas.dto.UsuarioDTO;
import br.edu.iff.ccc.appreceitas.model.Usuario;
import br.edu.iff.ccc.appreceitas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrar(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        return usuarioRepository.salvar(usuario);
    }

    public Optional<Usuario> autenticar(LoginDTO dto) {
        return usuarioRepository.listarTodos().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(dto.getEmail()))
                .filter(u -> u.getSenha().equals(dto.getSenha()))
                .findFirst();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.buscarPorId(id);
    }

    public boolean existeEmail(String email) {
        return usuarioRepository.listarTodos().stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }
}
