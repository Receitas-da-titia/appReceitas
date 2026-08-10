package br.edu.iff.ccc.appreceitas.repository.impl;

import br.edu.iff.ccc.appreceitas.model.Categoria;
import br.edu.iff.ccc.appreceitas.repository.CategoriaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryCategoriaRepository implements CategoriaRepository {

    private final Map<Long, Categoria> dados = new ConcurrentHashMap<>();
    private final AtomicLong proximoId = new AtomicLong(1);

    @Override
    public Categoria salvar(Categoria entidade) {
        Long id = proximoId.getAndIncrement();
        entidade.setIdCategoria(id);
        dados.put(id, entidade);
        return entidade;
    }

    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return Optional.ofNullable(dados.get(id));
    }

    @Override
    public List<Categoria> listarTodos() {
        return new ArrayList<>(dados.values());
    }

    @Override
    public Categoria atualizar(Categoria entidade) {
        dados.put(entidade.getIdCategoria(), entidade);
        return entidade;
    }

    @Override
    public void excluir(Long id) {
        dados.remove(id);
    }
}
