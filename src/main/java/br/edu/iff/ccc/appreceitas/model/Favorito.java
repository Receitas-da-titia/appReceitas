package br.edu.iff.ccc.appreceitas.model;

import java.time.LocalDateTime;

public class Favorito {

    private Long idFavorito;
    private LocalDateTime dataFavorito;
    private Long idUsuario;
    private Long idReceita;

    public Favorito() {
    }

    public Favorito(Long idFavorito, LocalDateTime dataFavorito, Long idUsuario, Long idReceita) {
        this.idFavorito = idFavorito;
        this.dataFavorito = dataFavorito;
        this.idUsuario = idUsuario;
        this.idReceita = idReceita;
    }

    public Long getIdFavorito() {
        return idFavorito;
    }

    public void setIdFavorito(Long idFavorito) {
        this.idFavorito = idFavorito;
    }

    public LocalDateTime getDataFavorito() {
        return dataFavorito;
    }

    public void setDataFavorito(LocalDateTime dataFavorito) {
        this.dataFavorito = dataFavorito;
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
