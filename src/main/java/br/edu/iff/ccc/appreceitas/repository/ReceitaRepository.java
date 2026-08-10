package br.edu.iff.ccc.appreceitas.repository;

import br.edu.iff.ccc.appreceitas.model.Receita;
import java.util.List;
import java.util.Optional;

public interface ReceitaRepository {

    Receita salvar(Receita entidade);

    Optional<Receita> buscarPorId(Long id);

    List<Receita> listarTodos();

    Receita atualizar(Receita entidade);

    void excluir(Long id);
}
