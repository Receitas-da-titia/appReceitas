package br.edu.iff.ccc.appreceitas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados necessários para cadastrar ou atualizar um ingrediente")
public class IngredienteDTO {

    @NotBlank(message = "O nome do ingrediente é obrigatório")
    @Size(min = 2, max = 60, message = "O nome deve ter entre 2 e 60 caracteres")
    @Schema(description = "Nome do ingrediente", example = "Farinha de trigo", minLength = 2, maxLength = 60)
    private String nomeIngrediente;

    public IngredienteDTO() {
    }

    public String getNomeIngrediente() {
        return nomeIngrediente;
    }

    public void setNomeIngrediente(String nomeIngrediente) {
        this.nomeIngrediente = nomeIngrediente;
    }
}
