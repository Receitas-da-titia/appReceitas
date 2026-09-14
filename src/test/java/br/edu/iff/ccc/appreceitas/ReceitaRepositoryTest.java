package br.edu.iff.ccc.appreceitas;

import br.edu.iff.ccc.appreceitas.model.Receita;
import br.edu.iff.ccc.appreceitas.repository.ReceitaRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReceitaRepositoryTest {

    @Autowired
    private ReceitaRepository receitaRepository;

    private Receita novaReceita(String nome) {
        return new Receita(null, nome, "Misture tudo e leve ao forno", 40, "bolo.jpg", 1L,
                List.of(1L, 2L));
    }

    @Test
    void deveSalvarReceitaComSucesso() {
        Receita receita = receitaRepository.save(novaReceita("Bolo de Cenoura"));

        assertNotNull(receita.getIdReceita());
        assertEquals("Bolo de Cenoura", receita.getNome());
        assertEquals(40, receita.getTempoPreparo());
        assertEquals(2, receita.getIdsIngredientes().size());
    }

    @Test
    void deveBuscarReceitaPorId() {
        Receita receita = receitaRepository.save(novaReceita("Torta de Limão"));

        Optional<Receita> encontrada = receitaRepository.findById(receita.getIdReceita());

        assertTrue(encontrada.isPresent());
        assertEquals("Torta de Limão", encontrada.get().getNome());
    }

    @Test
    void deveRetornarTrueQuandoNomeJaExiste() {
        receitaRepository.save(novaReceita("Pão de Queijo"));

        assertTrue(receitaRepository.existsByNomeIgnoreCase("pão de queijo"));
        assertFalse(receitaRepository.existsByNomeIgnoreCase("Brigadeiro"));
    }

    @Test
    void deveEncontrarReceitaComMesmoNomeIgnorandoAPropria() {
        Receita receita1 = receitaRepository.save(novaReceita("Feijoada"));
        Receita receita2 = receitaRepository.save(novaReceita("Feijoada Light"));

        // Simula uma edição da receita2 tentando usar o nome da receita1
        Optional<Receita> conflito = receitaRepository
                .findByNomeIgnoreCaseAndIdReceitaNot("Feijoada", receita2.getIdReceita());

        assertTrue(conflito.isPresent());
        assertEquals(receita1.getIdReceita(), conflito.get().getIdReceita());

        // A própria receita1 não deve gerar conflito consigo mesma
        Optional<Receita> semConflito = receitaRepository
                .findByNomeIgnoreCaseAndIdReceitaNot("Feijoada", receita1.getIdReceita());
        assertTrue(semConflito.isEmpty());
    }

    @Test
    void deveLancarExcecaoAoSalvarReceitaSemNomeObrigatorio() {
        Receita receita = new Receita(null, null, "Modo de preparo qualquer", 10, null, 1L, List.of());

        assertThrows(DataIntegrityViolationException.class,
                () -> receitaRepository.saveAndFlush(receita));
    }

    @Test
    void deveLancarExcecaoAoSalvarReceitaSemModoPreparoObrigatorio() {
        Receita receita = new Receita(null, "Receita sem modo de preparo", null, 10, null, 1L, List.of());

        assertThrows(DataIntegrityViolationException.class,
                () -> receitaRepository.saveAndFlush(receita));
    }

    @Test
    void deveListarTodasAsReceitasSalvas() {
        receitaRepository.save(novaReceita("Receita A"));
        receitaRepository.save(novaReceita("Receita B"));

        List<Receita> receitas = receitaRepository.findAll();

        assertEquals(2, receitas.size());
    }
}
