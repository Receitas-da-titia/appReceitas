package br.edu.iff.ccc.appreceitas.usecase;

import br.edu.iff.ccc.appreceitas.dto.ReceitaFormDTO;
import br.edu.iff.ccc.appreceitas.model.Receita;
import br.edu.iff.ccc.appreceitas.repository.ReceitaRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;


@Service
public class CadastrarReceitaUseCase {

    private final ReceitaRepository receitaRepository;
    private final AtomicLong gerandoIdentificador = new AtomicLong(0);

    public CadastrarReceitaUseCase(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    public Receita executar(ReceitaFormDTO dados) {
        if (dados.getNome() == null || dados.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome da receita é obrigatório.");
        }

        Long id = gerandoIdentificador.incrementAndGet();

        Receita receita = new Receita(
                id,
                dados.getNome(),
                dados.getIngredientes(),
                dados.getModoPreparo(),
                dados.getCategoria(),
                dados.getTempoPreparoMinutos(),
                dados.getImagemUrl()
        );

        return receitaRepository.salvar(receita);
    }
}
