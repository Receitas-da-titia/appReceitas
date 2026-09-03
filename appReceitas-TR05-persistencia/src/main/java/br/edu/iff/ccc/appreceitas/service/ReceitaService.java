package br.edu.iff.ccc.appreceitas.service;

import br.edu.iff.ccc.appreceitas.dto.ReceitaDTO;
import br.edu.iff.ccc.appreceitas.model.Receita;
import br.edu.iff.ccc.appreceitas.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.iff.ccc.appreceitas.exception. *;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    public Receita cadastrar(ReceitaDTO dto) {
        if (receitaRepository.existsByNomeIgnoreCase(dto.getNome())) {
            throw new EntidadeDuplicadaException("Já existe uma receita com o nome '" + dto.getNome() + "'");
        }
        Receita receita = new Receita();
        receita.setNome(dto.getNome());
        receita.setModoPreparo(dto.getModoPreparo());
        receita.setTempoPreparo(dto.getTempoPreparo());
        receita.setImagem(dto.getImagem());
        receita.setIdCategoria(dto.getIdCategoria());
        receita.setIdsIngredientes(dto.getIdsIngredientes());
        return receitaRepository.save(receita);
    }

    public Receita buscarPorIdOuFalhar(Long id) {
    return receitaRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Receita não encontrada com o id: " + id));
    }
    public Receita atualizar(Long id, ReceitaDTO dto) {
        Receita receita = buscarPorIdOuFalhar(id);
            if (receitaRepository.findByNomeIgnoreCaseAndIdReceitaNot(dto.getNome(), id).isPresent()) {
                throw new EntidadeDuplicadaException("Já existe outra receita com o nome '" + dto.getNome() + "'");
            }
        receita.setNome(dto.getNome());
        receita.setModoPreparo(dto.getModoPreparo());
        receita.setTempoPreparo(dto.getTempoPreparo());
        receita.setImagem(dto.getImagem());
        receita.setIdCategoria(dto.getIdCategoria());
        receita.setIdsIngredientes(dto.getIdsIngredientes());
        return receitaRepository.save(receita);
    }

    public void excluir(Long id) {
        buscarPorIdOuFalhar(id);
        receitaRepository.deleteById(id);
    }

    public Optional<Receita> buscarPorId(Long id) {
        return receitaRepository.findById(id);
    }

    public List<Receita> listarTodas() {
        return receitaRepository.findAll();
    }

    public List<Receita> buscar(String nome, Long idCategoria, Long idIngrediente) {
        return receitaRepository.findAll().stream()
                .filter(r -> nome == null || nome.isBlank()
                        || r.getNome().toLowerCase().contains(nome.toLowerCase()))
                .filter(r -> idCategoria == null || idCategoria.equals(r.getIdCategoria()))
                .filter(r -> idIngrediente == null || r.getIdsIngredientes().contains(idIngrediente))
                .collect(Collectors.toList());
    }
}
