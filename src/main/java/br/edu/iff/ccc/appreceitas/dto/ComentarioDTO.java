package br.edu.iff.ccc.appreceitas.dto;

public class ComentarioDTO {

    private int nota;
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
