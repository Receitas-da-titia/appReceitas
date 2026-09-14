package br.edu.iff.ccc.appreceitas.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração central da documentação OpenAPI/Swagger da API REST.
 * A UI interativa fica disponível em /swagger-ui.html e o contrato
 * em JSON em /v3/api-docs.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Receitas da Titia",
                version = "v1",
                description = "API REST para gerenciamento de receitas, ingredientes, categorias, "
                        + "usuários, comentários e favoritos do sistema Receitas da Titia.",
                contact = @Contact(name = "Equipe Receitas da Titia", email = "contato@receitasdatitia.iff.edu.br"),
                license = @License(name = "MIT", url = "https://opensource.org/licenses/MIT")
        )
)
public class OpenApiConfig {
}
