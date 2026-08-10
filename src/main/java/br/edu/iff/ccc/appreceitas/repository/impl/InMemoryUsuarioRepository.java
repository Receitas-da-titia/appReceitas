package br.edu.iff.ccc.appreceitas.repository.impl;

import br.edu.iff.ccc.appreceitas.model.Usuario;
import br.edu.iff.ccc.appreceitas.repository.UsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryUsuarioRepository implements UsuarioRepository {

    private final Map<Long, Usuario> dados = new ConcurrentHashMap<>();
    private final AtomicLong proximoId = new AtomicLong(1);

    @Override
    public Usuario salvar(Usuario entidade) {
        Long id = proximoId.getAndIncrement();
        entidade.setIdUsuario(id);
        dados.put(id, entidade);
        return entidade;
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return Optional.ofNullable(dados.get(id));
    }

    @Override
    public List<Usuario> listarTodos() {
        return new ArrayList<>(dados.values());
    }

    @Override
    public Usuario atualizar(Usuario entidade) {
        dados.put(entidade.getIdUsuario(), entidade);
        return entidade;
    }

    @Override
    public void excluir(Long id) {
        dados.remove(id);
    }
}
