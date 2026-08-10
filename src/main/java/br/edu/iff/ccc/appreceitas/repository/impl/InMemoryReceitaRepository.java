package br.edu.iff.ccc.appreceitas.repository.impl;

import br.edu.iff.ccc.appreceitas.model.Receita;
import br.edu.iff.ccc.appreceitas.repository.ReceitaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryReceitaRepository implements ReceitaRepository {

    private final Map<Long, Receita> dados = new ConcurrentHashMap<>();
    private final AtomicLong proximoId = new AtomicLong(1);

    @Override
    public Receita salvar(Receita entidade) {
        Long id = proximoId.getAndIncrement();
        entidade.setIdReceita(id);
        dados.put(id, entidade);
        return entidade;
    }

    @Override
    public Optional<Receita> buscarPorId(Long id) {
        return Optional.ofNullable(dados.get(id));
    }

    @Override
    public List<Receita> listarTodos() {
        return new ArrayList<>(dados.values());
    }

    @Override
    public Receita atualizar(Receita entidade) {
        dados.put(entidade.getIdReceita(), entidade);
        return entidade;
    }

    @Override
    public void excluir(Long id) {
        dados.remove(id);
    }
}
