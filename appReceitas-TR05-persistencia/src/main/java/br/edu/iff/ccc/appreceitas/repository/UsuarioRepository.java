package br.edu.iff.ccc.appreceitas.repository;

import br.edu.iff.ccc.appreceitas.model.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    Optional<Usuario> findByEmailIgnoreCaseAndSenha(String email, String senha);

    boolean existsByEmailIgnoreCase(String email);
}
