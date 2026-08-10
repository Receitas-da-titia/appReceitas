package br.edu.iff.ccc.appreceitas.service;

import br.edu.iff.ccc.appreceitas.dto.ReceitaDTO;
import br.edu.iff.ccc.appreceitas.model.Receita;
import br.edu.iff.ccc.appreceitas.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    public Receita cadastrar(ReceitaDTO dto) {
        Receita receita = new Receita();
        receita.setNome(dto.getNome());
        receita.setModoPreparo(dto.getModoPreparo());
        receita.setTempoPreparo(dto.getTempoPreparo());
        receita.setImagem(dto.getImagem());
        receita.setIdCategoria(dto.getIdCategoria());
        receita.setIdsIngredientes(dto.getIdsIngredientes());
        return receitaRepository.salvar(receita);
    }

    public Receita atualizar(Long id, ReceitaDTO dto) {
        Receita receita = receitaRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Receita não encontrada"));
        receita.setNome(dto.getNome());
        receita.setModoPreparo(dto.getModoPreparo());
        receita.setTempoPreparo(dto.getTempoPreparo());
        receita.setImagem(dto.getImagem());
        receita.setIdCategoria(dto.getIdCategoria());
        receita.setIdsIngredientes(dto.getIdsIngredientes());
        return receitaRepository.atualizar(receita);
    }

    public void excluir(Long id) {
        receitaRepository.excluir(id);
    }

    public Optional<Receita> buscarPorId(Long id) {
        return receitaRepository.buscarPorId(id);
    }

    public List<Receita> listarTodas() {
        return receitaRepository.listarTodos();
    }

    public List<Receita> buscar(String nome, Long idCategoria, Long idIngrediente) {
        return receitaRepository.listarTodos().stream()
                .filter(r -> nome == null || nome.isBlank()
                        || r.getNome().toLowerCase().contains(nome.toLowerCase()))
                .filter(r -> idCategoria == null || idCategoria.equals(r.getIdCategoria()))
                .filter(r -> idIngrediente == null || r.getIdsIngredientes().contains(idIngrediente))
                .collect(Collectors.toList());
    }
}
