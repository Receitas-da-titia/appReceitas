package br.edu.iff.ccc.appreceitas.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ComentarioDTO {

    @Min(value = 1, message = "A nota deve estar entre 1 e 5")
    @Max(value = 5, message = "A nota deve estar entre 1 e 5")
    private int nota;

    @NotBlank(message = "Escreva um comentário")
    @Size(min = 1, max = 500, message = "Comentário muito longo")
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
