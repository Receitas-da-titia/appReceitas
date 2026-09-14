package br.edu.iff.ccc.appreceitas.apirest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.appreceitas.model.Favorito;
import br.edu.iff.ccc.appreceitas.service.FavoritoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Endpoints RESTful para o gerenciamento dos favoritos de um usuário
 */
@RestController
@RequestMapping("/api/v1/favoritos")
@Tag(name = "Favoritos", description = "Gerenciamento das receitas favoritas de cada usuário")
public class FavoritoRestController {

    @Autowired
    private FavoritoService favoritoService;

    @GetMapping
    @Operation(summary = "Lista os favoritos de um usuário")
    @ApiResponse(responseCode = "200", description = "Lista de favoritos retornada com sucesso")
    public ResponseEntity<List<Favorito>> listarPorUsuario(
            @Parameter(description = "ID do usuário", example = "1") @RequestParam Long idUsuario) {
        return ResponseEntity.ok(favoritoService.listarPorUsuario(idUsuario));
    }

    @PostMapping
    @Operation(summary = "Adiciona uma receita aos favoritos de um usuário")
    @ApiResponse(responseCode = "201", description = "Favorito criado (ou já existente) com sucesso")
    public ResponseEntity<Favorito> adicionar(
            @Parameter(description = "ID do usuário", example = "1") @RequestParam Long idUsuario,
            @Parameter(description = "ID da receita", example = "1") @RequestParam Long idReceita) {
        Favorito favorito = favoritoService.adicionar(idUsuario, idReceita);
        return ResponseEntity.status(201).body(favorito);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um favorito pelo seu identificador")
    @ApiResponse(responseCode = "204", description = "Favorito removido com sucesso")
    public ResponseEntity<Void> remover(
            @Parameter(description = "ID do favorito", example = "1") @PathVariable Long id) {
        favoritoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
