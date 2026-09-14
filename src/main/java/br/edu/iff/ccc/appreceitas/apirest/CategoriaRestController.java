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

import br.edu.iff.ccc.appreceitas.dto.CategoriaDTO;
import br.edu.iff.ccc.appreceitas.model.Categoria;
import br.edu.iff.ccc.appreceitas.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Endpoints RESTful para o CRUD de categorias, independentes da camada MVC.
 */
@RestController
@RequestMapping("/api/v1/categorias")
@Tag(name = "Categorias", description = "Gerenciamento das categorias de receitas")
public class CategoriaRestController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "Lista todas as categorias cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso")
    public ResponseEntity<List<Categoria>> listar() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma categoria pelo seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria encontrada"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content)
    })
    public ResponseEntity<Categoria> buscarPorId(
            @Parameter(description = "ID da categoria", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarPorIdOuFalhar(id));
    }

    @PostMapping
    @Operation(summary = "Cadastra uma nova categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "409", description = "Categoria já cadastrada com esse nome", content = @Content)
    })
    public ResponseEntity<Categoria> cadastrar(@Valid @RequestBody CategoriaDTO dto) {
        Categoria categoria = categoriaService.cadastrar(dto);
        return ResponseEntity.created(URI.create("/api/v1/categorias/" + categoria.getIdCategoria()))
                .body(categoria);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma categoria existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content)
    })
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaDTO dto) {
        return ResponseEntity.ok(categoriaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma categoria pelo seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Categoria removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content)
    })
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        categoriaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
