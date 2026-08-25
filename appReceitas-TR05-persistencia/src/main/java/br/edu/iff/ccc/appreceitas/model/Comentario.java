package br.edu.iff.ccc.appreceitas.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "comentario")
public class Comentario {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

@Column (name = "id_avaliacao")
private Long idAvaliacao;

@Column (name = "nota" , nullable = false)
    private int nota;

@Column (name = "comentario", nullable = false)
    private String comentario;

@Column (name = "data_avaliacao", nullable = false)
    private LocalDateTime dataAvaliacao;

@Column (name = "id_usuario", nullable = false)
    private Long idUsuario;

@Column (name = "id_receita", nullable = false)
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
