package br.edu.iff.ccc.appreceitas.service;

import br.edu.iff.ccc.appreceitas.dto.CategoriaDTO;
import br.edu.iff.ccc.appreceitas.model.Categoria;
import br.edu.iff.ccc.appreceitas.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.iff.ccc.appreceitas.exception. *;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria cadastrar(CategoriaDTO dto) {
        if (categoriaRepository.existsByNomeCategoriaIgnoreCase(dto.getNomeCategoria())) {
            throw new EntidadeDuplicadaException("Categoria com esse nome já existe");
        }
        Categoria categoria = new Categoria();
        categoria.setNomeCategoria(dto.getNomeCategoria());
        return categoriaRepository.save(categoria);
    }

    public Categoria buscarPorIdOuFalhar(Long id){
    return categoriaRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada com o id: " + id));
    }
    
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria atualizar(Long id, CategoriaDTO dto) {
        Categoria categoria = buscarPorIdOuFalhar(id);
        categoria.setNomeCategoria(dto.getNomeCategoria());
        return categoriaRepository.save(categoria);
    }

    public void excluir(Long id) {
        buscarPorIdOuFalhar(id);
        categoriaRepository.deleteById(id);
    }
}
