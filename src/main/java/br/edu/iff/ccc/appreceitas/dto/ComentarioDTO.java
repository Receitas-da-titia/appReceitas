package br.edu.iff.ccc.appreceitas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados necessários para registrar um comentário/avaliação em uma receita")
public class ComentarioDTO {

    @Min(value = 1, message = "A nota deve estar entre 1 e 5")
    @Max(value = 5, message = "A nota deve estar entre 1 e 5")
    @Schema(description = "Nota atribuída à receita, de 1 a 5", example = "5", minimum = "1", maximum = "5")
    private int nota;

    @NotBlank(message = "Escreva um comentário")
    @Size(min = 1, max = 500, message = "Comentário muito longo")
    @Schema(description = "Texto do comentário", example = "Receita deliciosa e fácil de fazer!", maxLength = 500)
    private String comentario;

    public ComentarioDTO() {
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
