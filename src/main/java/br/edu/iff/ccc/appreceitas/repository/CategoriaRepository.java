package br.edu.iff.ccc.appreceitas.repository;

import br.edu.iff.ccc.appreceitas.model.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaRepository {

    Categoria salvar(Categoria entidade);

    Optional<Categoria> buscarPorId(Long id);

    List<Categoria> listarTodos();

    Categoria atualizar(Categoria entidade);

    void excluir(Long id);
}
