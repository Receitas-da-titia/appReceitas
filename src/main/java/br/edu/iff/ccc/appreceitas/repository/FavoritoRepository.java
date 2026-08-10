package br.edu.iff.ccc.appreceitas.repository;

import br.edu.iff.ccc.appreceitas.model.Favorito;
import java.util.List;
import java.util.Optional;

public interface FavoritoRepository {

    Favorito salvar(Favorito entidade);

    Optional<Favorito> buscarPorId(Long id);

    List<Favorito> listarTodos();

    Favorito atualizar(Favorito entidade);

    void excluir(Long id);
}
