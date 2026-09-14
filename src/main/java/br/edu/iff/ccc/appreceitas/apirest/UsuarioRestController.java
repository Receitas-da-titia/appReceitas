package br.edu.iff.ccc.appreceitas.apirest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.appreceitas.dto.LoginDTO;
import br.edu.iff.ccc.appreceitas.dto.UsuarioDTO;
import br.edu.iff.ccc.appreceitas.exception.RegraDeNegocioException;
import br.edu.iff.ccc.appreceitas.model.Usuario;
import br.edu.iff.ccc.appreceitas.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Endpoints RESTful para o CRUD de usuários e autenticação
 */
@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuários", description = "Gerenciamento e autenticação de usuários")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Lista todos os usuários cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um usuário pelo seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    public ResponseEntity<Usuario> buscarPorId(
            @Parameter(description = "ID do usuário", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "409", description = "E-mail já cadastrado", content = @Content)
    })
    public ResponseEntity<Usuario> cadastrar(@Valid @RequestBody UsuarioDTO dto) {
        Usuario usuario = usuarioService.cadastrar(dto);
        return ResponseEntity.created(URI.create("/api/v1/usuarios/" + usuario.getIdUsuario())).body(usuario);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um usuário existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content),
            @ApiResponse(responseCode = "409", description = "E-mail já cadastrado para outro usuário", content = @Content)
    })
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um usuário pelo seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    @Operation(summary = "Autentica um usuário a partir de e-mail e senha")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Credenciais inválidas", content = @Content)
    })
    public ResponseEntity<Usuario> login(@Valid @RequestBody LoginDTO dto) {
        Usuario usuario = usuarioService.autenticar(dto)
                .orElseThrow(() -> new RegraDeNegocioException("E-mail ou senha inválidos"));
        return ResponseEntity.ok(usuario);
    }
}
