package br.edu.iff.ccc.appreceitas;

import br.edu.iff.ccc.appreceitas.model.Comentario;
import br.edu.iff.ccc.appreceitas.repository.ComentarioRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ComentarioRepositoryTest {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Test
    void deveSalvarComentarioComSucesso() {
        Comentario comentario = comentarioRepository.save(
                new Comentario(null, 5, "Receita deliciosa!", LocalDateTime.now(), 1L, 10L));

        assertNotNull(comentario.getIdAvaliacao());
        assertEquals(5, comentario.getNota());
        assertEquals("Receita deliciosa!", comentario.getComentario());
    }

    @Test
    void deveBuscarComentarioPorId() {
        Comentario comentario = comentarioRepository.save(
                new Comentario(null, 4, "Muito bom", LocalDateTime.now(), 2L, 20L));

        Optional<Comentario> encontrado = comentarioRepository.findById(comentario.getIdAvaliacao());

        assertTrue(encontrado.isPresent());
        assertEquals("Muito bom", encontrado.get().getComentario());
    }

    @Test
    void deveListarComentariosPorIdReceita() {
        comentarioRepository.save(new Comentario(null, 5, "Ótima!", LocalDateTime.now(), 1L, 99L));
        comentarioRepository.save(new Comentario(null, 3, "Poderia ser melhor", LocalDateTime.now(), 2L, 99L));
        comentarioRepository.save(new Comentario(null, 4, "Boa receita", LocalDateTime.now(), 3L, 100L));

        List<Comentario> comentariosDaReceita99 = comentarioRepository.findByIdReceita(99L);

        assertEquals(2, comentariosDaReceita99.size());
        assertTrue(comentariosDaReceita99.stream().allMatch(c -> c.getIdReceita().equals(99L)));
    }

    @Test
    void deveLancarExcecaoAoSalvarComentarioSemTextoObrigatorio() {
        Comentario comentario = new Comentario(null, 5, null, LocalDateTime.now(), 1L, 10L);

        assertThrows(DataIntegrityViolationException.class,
                () -> comentarioRepository.saveAndFlush(comentario));
    }

    @Test
    void deveLancarExcecaoAoSalvarComentarioSemDataAvaliacaoObrigatoria() {
        Comentario comentario = new Comentario(null, 5, "Sem data", null, 1L, 10L);

        assertThrows(DataIntegrityViolationException.class,
                () -> comentarioRepository.saveAndFlush(comentario));
    }

    @Test
    void deveExcluirComentarioPorId() {
        Comentario comentario = comentarioRepository.save(
                new Comentario(null, 2, "Não gostei muito", LocalDateTime.now(), 1L, 5L));

        comentarioRepository.deleteById(comentario.getIdAvaliacao());

        assertTrue(comentarioRepository.findById(comentario.getIdAvaliacao()).isEmpty());
    }
}
