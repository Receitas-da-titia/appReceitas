package br.edu.iff.ccc.appreceitas;
import br.edu.iff.ccc.appreceitas.model.Categoria;
import br.edu.iff.ccc.appreceitas.repository.CategoriaRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    void deveSalvarCategoriaComSucesso() {
        Categoria categoria = new Categoria(null, "Sobremesas");
        Categoria salva = categoriaRepository.save(categoria);

        assertNotNull(salva.getIdCategoria());
        assertEquals("Sobremesas", salva.getNomeCategoria());
    }

    @Test
    void deveBuscarCategoriaPorId() {
        Categoria categoria = categoriaRepository.save(new Categoria(null, "Massas"));

        Optional<Categoria> encontrada = categoriaRepository.findById(categoria.getIdCategoria());

        assertTrue(encontrada.isPresent());
        assertEquals("Massas", encontrada.get().getNomeCategoria());
    }

    @Test
    void deveLancarExcecaoAoSalvarNomeDuplicado() {
        categoriaRepository.saveAndFlush(new Categoria(null, "Bebidas"));

        assertThrows(DataIntegrityViolationException.class, 
            () -> categoriaRepository.saveAndFlush(new Categoria(null, "Bebidas")));
    }
}