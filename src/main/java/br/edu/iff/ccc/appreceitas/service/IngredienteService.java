package br.edu.iff.ccc.appreceitas.service;

import br.edu.iff.ccc.appreceitas.dto.IngredienteDTO;
import br.edu.iff.ccc.appreceitas.model.Ingrediente;
import br.edu.iff.ccc.appreceitas.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public Ingrediente cadastrar(IngredienteDTO dto) {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNomeIngrediente(dto.getNomeIngrediente());
        return ingredienteRepository.salvar(ingrediente);
    }

    public List<Ingrediente> listarTodos() {
        return ingredienteRepository.listarTodos();
    }

    public Optional<Ingrediente> buscarPorId(Long id) {
        return ingredienteRepository.buscarPorId(id);
    }

    public Ingrediente atualizar(Long id, IngredienteDTO dto) {
        Ingrediente ingrediente = ingredienteRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Ingrediente não encontrado"));
        ingrediente.setNomeIngrediente(dto.getNomeIngrediente());
        return ingredienteRepository.atualizar(ingrediente);
    }

    public void excluir(Long id) {
        ingredienteRepository.excluir(id);
    }
}
