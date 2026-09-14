package br.edu.iff.ccc.appreceitas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados necessários para cadastrar ou atualizar uma categoria de receitas")
public class CategoriaDTO {

    @NotBlank(message = "O nome da categoria é obrigatório")
    @Size(min = 2, max = 60, message = "O nome deve ter entre 2 e 60 caracteres")
    @Schema(description = "Nome da categoria", example = "Sobremesas", minLength = 2, maxLength = 60)
    private String nomeCategoria;

    public CategoriaDTO() {
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
}
