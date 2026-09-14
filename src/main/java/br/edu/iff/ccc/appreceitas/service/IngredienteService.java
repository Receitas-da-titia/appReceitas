package br.edu.iff.ccc.appreceitas.service;

import br.edu.iff.ccc.appreceitas.dto.IngredienteDTO;
import br.edu.iff.ccc.appreceitas.model.Ingrediente;
import br.edu.iff.ccc.appreceitas.repository.IngredienteRepository;
import br.edu.iff.ccc.appreceitas.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public Ingrediente cadastrar(IngredienteDTO dto) {
        if (ingredienteRepository.existsByNomeIngredienteIgnoreCase(dto.getNomeIngrediente())) {
            throw new EntidadeDuplicadaException("Ingrediente com esse nome já existe");
        }
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNomeIngrediente(dto.getNomeIngrediente());
        return ingredienteRepository.save(ingrediente);
    }

    public Ingrediente buscarPorIdOuFalhar(Long id) {
        return ingredienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Ingrediente não encontrado com o id: " + id));
    }

    public List<Ingrediente> listarTodos() {
        return ingredienteRepository.findAll();
    }

    public Optional<Ingrediente> buscarPorId(Long id) {
        return ingredienteRepository.findById(id);
    }

    public Ingrediente atualizar(Long id, IngredienteDTO dto) {
        Ingrediente ingrediente = buscarPorIdOuFalhar(id);
        ingrediente.setNomeIngrediente(dto.getNomeIngrediente());
        return ingredienteRepository.save(ingrediente);
    }

    public void excluir(Long id) {
        buscarPorIdOuFalhar(id);
        ingredienteRepository.deleteById(id);
    }
}