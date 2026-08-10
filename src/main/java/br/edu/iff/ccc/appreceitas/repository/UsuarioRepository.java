package br.edu.iff.ccc.appreceitas.repository;

import br.edu.iff.ccc.appreceitas.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {

    Usuario salvar(Usuario entidade);

    Optional<Usuario> buscarPorId(Long id);

    List<Usuario> listarTodos();

    Usuario atualizar(Usuario entidade);

    void excluir(Long id);
}
