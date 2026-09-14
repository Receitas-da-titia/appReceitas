package br.edu.iff.ccc.appreceitas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ingrediente")
public class Ingrediente {

@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
@Column(name = "id_igrediente")
private Long idIngrediente;

@Column(name = "nomeIngrediente", nullable = false, unique = true)
private String nomeIngrediente;


    public Ingrediente() {
    }

    public Ingrediente(Long idIngrediente, String nomeIngrediente) {
        this.idIngrediente = idIngrediente;
        this.nomeIngrediente = nomeIngrediente;
    }

    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNomeIngrediente() {
        return nomeIngrediente;
    }

    public void setNomeIngrediente(String nomeIngrediente) {
        this.nomeIngrediente = nomeIngrediente;
    }
}
