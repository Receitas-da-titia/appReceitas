package br.edu.iff.ccc.appreceitas.apirest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.appreceitas.dto.ComentarioDTO;
import br.edu.iff.ccc.appreceitas.model.Comentario;
import br.edu.iff.ccc.appreceitas.service.ComentarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Endpoints RESTful para o gerenciamento de comentários/avaliações de
 * receitas, independentes da camada MVC
 */
@RestController
@Tag(name = "Comentários", description = "Gerenciamento dos comentários e avaliações das receitas")
public class ComentarioRestController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/api/v1/receitas/{idReceita}/comentarios")
    @Operation(summary = "Lista os comentários de uma receita")
    @ApiResponse(responseCode = "200", description = "Lista de comentários retornada com sucesso")
    public ResponseEntity<List<Comentario>> listarPorReceita(
            @Parameter(description = "ID da receita", example = "1") @PathVariable Long idReceita) {
        return ResponseEntity.ok(comentarioService.listarPorReceita(idReceita));
    }

    @PostMapping("/api/v1/receitas/{idReceita}/comentarios")
    @Operation(summary = "Adiciona um comentário/avaliação a uma receita")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Comentário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<Comentario> adicionar(
            @Parameter(description = "ID da receita comentada", example = "1") @PathVariable Long idReceita,
            @Parameter(description = "ID do usuário autor do comentário", example = "1")
            @RequestParam Long idUsuario,
            @Valid @RequestBody ComentarioDTO dto) {
        Comentario comentario = comentarioService.adicionar(idUsuario, idReceita, dto);
        return ResponseEntity.status(201).body(comentario);
    }

    @DeleteMapping("/api/v1/comentarios/{id}")
    @Operation(summary = "Remove um comentário pelo seu identificador")
    @ApiResponse(responseCode = "204", description = "Comentário removido com sucesso")
    public ResponseEntity<Void> excluir(
            @Parameter(description = "ID do comentário", example = "1") @PathVariable Long id) {
        comentarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
