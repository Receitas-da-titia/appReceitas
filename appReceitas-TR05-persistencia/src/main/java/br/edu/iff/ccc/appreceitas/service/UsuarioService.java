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
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> autenticar(LoginDTO dto) {
       return usuarioRepository.findByEmailIgnoreCaseAndSenha(dto.getEmail(), dto.getSenha());
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }

    public boolean existeEmail(String email) {
        return usuarioRepository.existsByEmailIgnoreCase(email);
    }
}
