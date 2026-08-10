package br.edu.iff.ccc.appreceitas.repository.impl;

import br.edu.iff.ccc.appreceitas.model.Ingrediente;
import br.edu.iff.ccc.appreceitas.repository.IngredienteRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryIngredienteRepository implements IngredienteRepository {

    private final Map<Long, Ingrediente> dados = new ConcurrentHashMap<>();
    private final AtomicLong proximoId = new AtomicLong(1);

    @Override
    public Ingrediente salvar(Ingrediente entidade) {
        Long id = proximoId.getAndIncrement();
        entidade.setIdIngrediente(id);
        dados.put(id, entidade);
        return entidade;
    }

    @Override
    public Optional<Ingrediente> buscarPorId(Long id) {
        return Optional.ofNullable(dados.get(id));
    }

    @Override
    public List<Ingrediente> listarTodos() {
        return new ArrayList<>(dados.values());
    }

    @Override
    public Ingrediente atualizar(Ingrediente entidade) {
        dados.put(entidade.getIdIngrediente(), entidade);
        return entidade;
    }

    @Override
    public void excluir(Long id) {
        dados.remove(id);
    }
}
