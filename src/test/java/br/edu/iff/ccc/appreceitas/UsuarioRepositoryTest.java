package br.edu.iff.ccc.appreceitas;

import br.edu.iff.ccc.appreceitas.model.Usuario;
import br.edu.iff.ccc.appreceitas.repository.UsuarioRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void deveSalvarUsuarioComSucesso() {
        Usuario usuario = usuarioRepository.save(
                new Usuario(null, "Maria Silva", "maria@email.com", "senha123"));

        assertNotNull(usuario.getIdUsuario());
        assertEquals("Maria Silva", usuario.getNome());
        assertEquals("maria@email.com", usuario.getEmail());
    }

    @Test
    void deveBuscarUsuarioPorId() {
        Usuario usuario = usuarioRepository.save(
                new Usuario(null, "João Souza", "joao@email.com", "senha123"));

        Optional<Usuario> encontrado = usuarioRepository.findById(usuario.getIdUsuario());

        assertTrue(encontrado.isPresent());
        assertEquals("joao@email.com", encontrado.get().getEmail());
    }

    @Test
    void deveAutenticarUsuarioComEmailESenhaCorretos() {
        usuarioRepository.save(new Usuario(null, "Ana Paula", "ana@email.com", "minhaSenha"));

        Optional<Usuario> autenticado = usuarioRepository
                .findByEmailIgnoreCaseAndSenha("ANA@EMAIL.COM", "minhaSenha");

        assertTrue(autenticado.isPresent());
        assertEquals("Ana Paula", autenticado.get().getNome());
    }

    @Test
    void naoDeveAutenticarUsuarioComSenhaIncorreta() {
        usuarioRepository.save(new Usuario(null, "Carlos Lima", "carlos@email.com", "senhaCerta"));

        Optional<Usuario> autenticado = usuarioRepository
                .findByEmailIgnoreCaseAndSenha("carlos@email.com", "senhaErrada");

        assertTrue(autenticado.isEmpty());
    }

    @Test
    void deveRetornarTrueQuandoEmailJaCadastrado() {
        usuarioRepository.save(new Usuario(null, "Pedro Alves", "pedro@email.com", "123456"));

        assertTrue(usuarioRepository.existsByEmailIgnoreCase("PEDRO@EMAIL.COM"));
        assertFalse(usuarioRepository.existsByEmailIgnoreCase("outro@email.com"));
    }

    @Test
    void deveLancarExcecaoAoSalvarEmailDuplicado() {
        usuarioRepository.saveAndFlush(new Usuario(null, "Usuário 1", "duplicado@email.com", "senha1"));

        Usuario duplicado = new Usuario(null, "Usuário 2", "duplicado@email.com", "senha2");

        assertThrows(DataIntegrityViolationException.class,
                () -> usuarioRepository.saveAndFlush(duplicado));
    }

    @Test
    void deveLancarExcecaoAoSalvarUsuarioSemNomeObrigatorio() {
        Usuario usuario = new Usuario(null, null, "semnome@email.com", "senha123");

        assertThrows(DataIntegrityViolationException.class,
                () -> usuarioRepository.saveAndFlush(usuario));
    }

    @Test
    void deveLancarExcecaoAoSalvarUsuarioSemSenhaObrigatoria() {
        Usuario usuario = new Usuario(null, "Sem Senha", "semsenha@email.com", null);

        assertThrows(DataIntegrityViolationException.class,
                () -> usuarioRepository.saveAndFlush(usuario));
    }
}
