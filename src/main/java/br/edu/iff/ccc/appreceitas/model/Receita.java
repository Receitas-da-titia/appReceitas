package br.edu.iff.ccc.appreceitas.model;

import java.util.ArrayList;
import java.util.List;

public class Receita {

    private Long idReceita;
    private String nome;
    private String modoPreparo;
    private int tempoPreparo;
    private String imagem;
    private Long idCategoria;
    private List<Long> idsIngredientes = new ArrayList<>();

    public Receita() {
    }

    public Receita(Long idReceita, String nome, String modoPreparo, int tempoPreparo,
                    String imagem, Long idCategoria, List<Long> idsIngredientes) {
        this.idReceita = idReceita;
        this.nome = nome;
        this.modoPreparo = modoPreparo;
        this.tempoPreparo = tempoPreparo;
        this.imagem = imagem;
        this.idCategoria = idCategoria;
        this.idsIngredientes = idsIngredientes != null ? idsIngredientes : new ArrayList<>();
    }

    public Long getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Long idReceita) {
        this.idReceita = idReceita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(String modoPreparo) {
        this.modoPreparo = modoPreparo;
    }

    public int getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(int tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public List<Long> getIdsIngredientes() {
        return idsIngredientes;
    }

    public void setIdsIngredientes(List<Long> idsIngredientes) {
        this.idsIngredientes = idsIngredientes != null ? idsIngredientes : new ArrayList<>();
    }
}
