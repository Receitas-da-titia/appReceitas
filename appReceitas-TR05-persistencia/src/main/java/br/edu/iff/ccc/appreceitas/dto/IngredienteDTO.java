package br.edu.iff.ccc.appreceitas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class IngredienteDTO {
    @NotBlank(message = "O nome do ingrediente é obrigatório")
    @Size(min = 2, max = 60, message = "O nome deve ter entre 2 e 60 caracteres")
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
