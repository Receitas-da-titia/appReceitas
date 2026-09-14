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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.appreceitas.dto.ReceitaDTO;
import br.edu.iff.ccc.appreceitas.model.Receita;
import br.edu.iff.ccc.appreceitas.service.ReceitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Endpoints RESTful para o CRUD de receitas
 */
@RestController
@RequestMapping("/api/v1/receitas")
@Tag(name = "Receitas", description = "Gerenciamento das receitas cadastradas no sistema")
public class ReceitaRestController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping
    @Operation(summary = "Lista receitas, com filtros opcionais por nome, categoria e ingrediente")
    @ApiResponse(responseCode = "200", description = "Lista de receitas retornada com sucesso")
    public ResponseEntity<List<Receita>> listar(
            @Parameter(description = "Filtra pelo nome (contém, sem diferenciar maiúsculas/minúsculas)")
            @RequestParam(required = false) String nome,
            @Parameter(description = "Filtra pelo ID da categoria") @RequestParam(required = false) Long idCategoria,
            @Parameter(description = "Filtra por receitas que contenham o ingrediente informado")
            @RequestParam(required = false) Long idIngrediente) {
        return ResponseEntity.ok(receitaService.buscar(nome, idCategoria, idIngrediente));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma receita pelo seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Receita encontrada"),
            @ApiResponse(responseCode = "404", description = "Receita não encontrada", content = @Content)
    })
    public ResponseEntity<Receita> buscarPorId(
            @Parameter(description = "ID da receita", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(receitaService.buscarPorIdOuFalhar(id));
    }

    @PostMapping
    @Operation(summary = "Cadastra uma nova receita")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Receita criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "409", description = "Já existe receita com esse nome", content = @Content)
    })
    public ResponseEntity<Receita> cadastrar(@Valid @RequestBody ReceitaDTO dto) {
        Receita receita = receitaService.cadastrar(dto);
        return ResponseEntity.created(URI.create("/api/v1/receitas/" + receita.getIdReceita())).body(receita);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma receita existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Receita atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Receita não encontrada", content = @Content),
            @ApiResponse(responseCode = "409", description = "Já existe outra receita com esse nome", content = @Content)
    })
    public ResponseEntity<Receita> atualizar(@PathVariable Long id, @Valid @RequestBody ReceitaDTO dto) {
        return ResponseEntity.ok(receitaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma receita pelo seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Receita removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Receita não encontrada", content = @Content)
    })
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        receitaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
