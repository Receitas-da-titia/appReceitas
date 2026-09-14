package br.edu.iff.ccc.appreceitas.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "favorito")

public class Favorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column (name = "id_favorito")
    private Long idFavorito;

    @Column (name = "data_favorito", nullable = false)
    private LocalDateTime dataFavorito;

    @Column (name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column (name = "id_receita" , nullable = false)
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
