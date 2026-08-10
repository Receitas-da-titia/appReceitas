package br.edu.iff.ccc.appreceitas.repository;

import br.edu.iff.ccc.appreceitas.model.Ingrediente;
import java.util.List;
import java.util.Optional;

public interface IngredienteRepository {

    Ingrediente salvar(Ingrediente entidade);

    Optional<Ingrediente> buscarPorId(Long id);

    List<Ingrediente> listarTodos();

    Ingrediente atualizar(Ingrediente entidade);

    void excluir(Long id);
}
