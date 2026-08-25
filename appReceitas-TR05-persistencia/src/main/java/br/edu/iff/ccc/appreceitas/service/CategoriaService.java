package br.edu.iff.ccc.appreceitas.service;

import br.edu.iff.ccc.appreceitas.dto.CategoriaDTO;
import br.edu.iff.ccc.appreceitas.model.Categoria;
import br.edu.iff.ccc.appreceitas.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria cadastrar(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNomeCategoria(dto.getNomeCategoria());
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria atualizar(Long id, CategoriaDTO dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
        categoria.setNomeCategoria(dto.getNomeCategoria());
        return categoriaRepository.save(categoria);
    }

    public void excluir(Long id) {
        categoriaRepository.deleteById(id);
    }
}
