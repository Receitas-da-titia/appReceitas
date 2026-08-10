package br.edu.iff.ccc.appreceitas.usecase;

import br.edu.iff.ccc.appreceitas.model.Receita;
import br.edu.iff.ccc.appreceitas.repository.ReceitaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BuscarReceitaUseCase {

    private final ReceitaRepository receitaRepository;

    public BuscarReceitaUseCase(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    public Optional<Receita> executar(Long id) {
        return receitaRepository.buscarPorId(id);
    }
}
