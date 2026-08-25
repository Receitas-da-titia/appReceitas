package br.edu.iff.ccc.appreceitas.dto;

import java.util.ArrayList;
import java.util.List;

public class ReceitaDTO {

    private String nome;
    private String modoPreparo;
    private int tempoPreparo;
    private String imagem;
    private Long idCategoria;
    private List<Long> idsIngredientes = new ArrayList<>();

    public ReceitaDTO() {
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
        this.idsIngredientes = idsIngredientes;
    }
}
