package br.edu.iff.ccc.appreceitas.model;

import java.time.LocalDateTime;

public class Comentario {

    private Long idAvaliacao;
    private int nota;
    private String comentario;
    private LocalDateTime dataAvaliacao;
    private Long idUsuario;
    private Long idReceita;

    public Comentario() {
    }

    public Comentario(Long idAvaliacao, int nota, String comentario, LocalDateTime dataAvaliacao,
                       Long idUsuario, Long idReceita) {
        this.idAvaliacao = idAvaliacao;
        this.nota = nota;
        this.comentario = comentario;
        this.dataAvaliacao = dataAvaliacao;
        this.idUsuario = idUsuario;
        this.idReceita = idReceita;
    }

    public Long getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Long idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
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

    public LocalDateTime getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Long idReceita) {
        this.idReceita = idReceita;
    }
}
