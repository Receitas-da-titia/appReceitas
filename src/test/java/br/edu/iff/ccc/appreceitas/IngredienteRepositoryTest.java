package br.edu.iff.ccc.appreceitas;

import br.edu.iff.ccc.appreceitas.model.Ingrediente;
import br.edu.iff.ccc.appreceitas.repository.IngredienteRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IngredienteRepositoryTest {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Test
    void deveSalvarIngredienteComSucesso() {
        Ingrediente ingrediente = ingredienteRepository.save(new Ingrediente(null, "Farinha de trigo"));

        assertNotNull(ingrediente.getIdIngrediente());
        assertEquals("Farinha de trigo", ingrediente.getNomeIngrediente());
    }

    @Test
    void deveBuscarIngredientePorId() {
        Ingrediente ingrediente = ingredienteRepository.save(new Ingrediente(null, "Ovos"));

        Optional<Ingrediente> encontrado = ingredienteRepository.findById(ingrediente.getIdIngrediente());

        assertTrue(encontrado.isPresent());
        assertEquals("Ovos", encontrado.get().getNomeIngrediente());
    }

    @Test
    void deveRetornarTrueQuandoNomeDeIngredienteJaExiste() {
        ingredienteRepository.save(new Ingrediente(null, "Açúcar"));

        assertTrue(ingredienteRepository.existsByNomeIngredienteIgnoreCase("açúcar"));
        assertFalse(ingredienteRepository.existsByNomeIngredienteIgnoreCase("Sal"));
    }

    @Test
    void deveLancarExcecaoAoSalvarNomeDeIngredienteDuplicado() {
        ingredienteRepository.saveAndFlush(new Ingrediente(null, "Leite Condensado"));

        assertThrows(DataIntegrityViolationException.class,
                () -> ingredienteRepository.saveAndFlush(new Ingrediente(null, "Leite Condensado")));
    }

    @Test
    void deveLancarExcecaoAoSalvarIngredienteSemNomeObrigatorio() {
        Ingrediente ingrediente = new Ingrediente(null, null);

        assertThrows(DataIntegrityViolationException.class,
                () -> ingredienteRepository.saveAndFlush(ingrediente));
    }

    @Test
    void deveListarTodosOsIngredientes() {
        ingredienteRepository.save(new Ingrediente(null, "Manteiga"));
        ingredienteRepository.save(new Ingrediente(null, "Chocolate"));

        List<Ingrediente> ingredientes = ingredienteRepository.findAll();

        assertEquals(2, ingredientes.size());
    }

    @Test
    void deveExcluirIngredientePorId() {
        Ingrediente ingrediente = ingredienteRepository.save(new Ingrediente(null, "Baunilha"));

        ingredienteRepository.deleteById(ingrediente.getIdIngrediente());

        assertTrue(ingredienteRepository.findById(ingrediente.getIdIngrediente()).isEmpty());
    }
}
