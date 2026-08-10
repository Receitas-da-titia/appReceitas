package br.edu.iff.ccc.appreceitas.repository;

import br.edu.iff.ccc.appreceitas.model.Receita;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class ReceitaRepository {

    private final Map<Long, Receita> receitas = new ConcurrentHashMap<>();

    public Receita salvar(Receita receita) {
        receitas.put(receita.getId(), receita);
        return receita;
    }

    public List<Receita> listarTodas() {
        return new ArrayList<>(receitas.values());
    }

    public Optional<Receita> buscarPorId(Long id) {
        return Optional.ofNullable(receitas.get(id));
    }

    public void remover(Long id) {
        receitas.remove(id);
    }
}
