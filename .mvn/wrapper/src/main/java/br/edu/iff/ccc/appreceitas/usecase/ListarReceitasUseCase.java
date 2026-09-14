package br.edu.iff.ccc.appreceitas.usecase;

import br.edu.iff.ccc.appreceitas.model.Receita;
import br.edu.iff.ccc.appreceitas.repository.ReceitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ListarReceitasUseCase {

    private final ReceitaRepository receitaRepository;

    public ListarReceitasUseCase(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    public List<Receita> executar(String nome, String ingrediente, String categoria) {
        return receitaRepository.listarTodas().stream()
                .filter(receita -> correspondeAoFiltro(receita.getNome(), nome))
                .filter(receita -> correspondeAoFiltro(receita.getIngredientes(), ingrediente))
                .filter(receita -> correspondeAoFiltro(receita.getCategoria(), categoria))
                .toList();
    }

    private boolean correspondeAoFiltro(String campo, String filtro) {
        if (filtro == null || filtro.isBlank()) {
            return true;
        }
        return campo != null && campo.toLowerCase().contains(filtro.toLowerCase());
    }
}
