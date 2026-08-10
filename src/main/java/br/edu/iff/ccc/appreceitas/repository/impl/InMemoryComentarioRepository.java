package br.edu.iff.ccc.appreceitas.repository.impl;

import br.edu.iff.ccc.appreceitas.model.Comentario;
import br.edu.iff.ccc.appreceitas.repository.ComentarioRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryComentarioRepository implements ComentarioRepository {

    private final Map<Long, Comentario> dados = new ConcurrentHashMap<>();
    private final AtomicLong proximoId = new AtomicLong(1);

    @Override
    public Comentario salvar(Comentario entidade) {
        Long id = proximoId.getAndIncrement();
        entidade.setIdAvaliacao(id);
        dados.put(id, entidade);
        return entidade;
    }

    @Override
    public Optional<Comentario> buscarPorId(Long id) {
        return Optional.ofNullable(dados.get(id));
    }

    @Override
    public List<Comentario> listarTodos() {
        return new ArrayList<>(dados.values());
    }

    @Override
    public Comentario atualizar(Comentario entidade) {
        dados.put(entidade.getIdAvaliacao(), entidade);
        return entidade;
    }

    @Override
    public void excluir(Long id) {
        dados.remove(id);
    }
}
