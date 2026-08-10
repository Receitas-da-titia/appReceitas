package br.edu.iff.ccc.appreceitas.repository.impl;

import br.edu.iff.ccc.appreceitas.model.Favorito;
import br.edu.iff.ccc.appreceitas.repository.FavoritoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryFavoritoRepository implements FavoritoRepository {

    private final Map<Long, Favorito> dados = new ConcurrentHashMap<>();
    private final AtomicLong proximoId = new AtomicLong(1);

    @Override
    public Favorito salvar(Favorito entidade) {
        Long id = proximoId.getAndIncrement();
        entidade.setIdFavorito(id);
        dados.put(id, entidade);
        return entidade;
    }

    @Override
    public Optional<Favorito> buscarPorId(Long id) {
        return Optional.ofNullable(dados.get(id));
    }

    @Override
    public List<Favorito> listarTodos() {
        return new ArrayList<>(dados.values());
    }

    @Override
    public Favorito atualizar(Favorito entidade) {
        dados.put(entidade.getIdFavorito(), entidade);
        return entidade;
    }

    @Override
    public void excluir(Long id) {
        dados.remove(id);
    }
}
