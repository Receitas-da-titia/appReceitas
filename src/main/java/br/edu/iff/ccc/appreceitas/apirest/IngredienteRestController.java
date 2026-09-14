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

import br.edu.iff.ccc.appreceitas.dto.IngredienteDTO;
import br.edu.iff.ccc.appreceitas.model.Ingrediente;
import br.edu.iff.ccc.appreceitas.service.IngredienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Endpoints RESTful para o CRUD de ingredientes
 */
@RestController
@RequestMapping("/api/v1/ingredientes")
@Tag(name = "Ingredientes", description = "Gerenciamento dos ingredientes cadastrados no sistema")
public class IngredienteRestController {

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    @Operation(summary = "Lista todos os ingredientes cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de ingredientes retornada com sucesso")
    public ResponseEntity<List<Ingrediente>> listar() {
        return ResponseEntity.ok(ingredienteService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um ingrediente pelo seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingrediente encontrado"),
            @ApiResponse(responseCode = "404", description = "Ingrediente não encontrado", content = @Content)
    })
    public ResponseEntity<Ingrediente> buscarPorId(
            @Parameter(description = "ID do ingrediente", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(ingredienteService.buscarPorIdOuFalhar(id));
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo ingrediente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Ingrediente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "409", description = "Ingrediente já cadastrado com esse nome", content = @Content)
    })
    public ResponseEntity<Ingrediente> cadastrar(@Valid @RequestBody IngredienteDTO dto) {
        Ingrediente ingrediente = ingredienteService.cadastrar(dto);
        return ResponseEntity.created(URI.create("/api/v1/ingredientes/" + ingrediente.getIdIngrediente()))
                .body(ingrediente);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um ingrediente existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingrediente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ingrediente não encontrado", content = @Content)
    })
    public ResponseEntity<Ingrediente> atualizar(@PathVariable Long id, @Valid @RequestBody IngredienteDTO dto) {
        return ResponseEntity.ok(ingredienteService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um ingrediente pelo seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Ingrediente removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ingrediente não encontrado", content = @Content)
    })
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        ingredienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
