package br.edu.iff.ccc.appreceitas.repository;

import br.edu.iff.ccc.appreceitas.model.Comentario;
import java.util.List;
import java.util.Optional;

public interface ComentarioRepository {

    Comentario salvar(Comentario entidade);

    Optional<Comentario> buscarPorId(Long id);

    List<Comentario> listarTodos();

    Comentario atualizar(Comentario entidade);

    void excluir(Long id);
}
