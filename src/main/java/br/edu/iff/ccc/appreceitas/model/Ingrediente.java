package br.edu.iff.ccc.appreceitas.model;

public class Ingrediente {

    private Long idIngrediente;
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
