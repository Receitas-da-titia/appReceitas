package br.edu.iff.ccc.appreceitas.apirest;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Endpoint raiz da API REST, útil como health-check simples do serviço.
 * Os recursos de negócio (categorias, receitas, ingredientes, usuários,
 * comentários e favoritos) possuem seus próprios controllers dedicados
 * neste mesmo pacote.
 */
@RestController
@Tag(name = "Status da API", description = "Informações gerais e verificação de disponibilidade da API")
public class ApiRestController {

    @GetMapping("/api/v1")
    @Operation(summary = "Verifica o status da API", description = "Endpoint simples de health-check da API REST.")
    @ApiResponse(responseCode = "200", description = "API disponível")
    public Map<String, String> apiStatus() {
        return Map.of("status", "API Receitas da Titia em funcionamento", "versao", "v1");
    }
}
