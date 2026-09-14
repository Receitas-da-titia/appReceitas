package br.edu.iff.ccc.appreceitas.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ReceitaDTO {

    @NotEmpty(message = "Receita deve ter pelo menos um ingrediente")
    private List<Long> idsIngredientes = new ArrayList<>();
    
    @NotBlank(message = "Informe o nome da receita")
    private String nome;

    @NotBlank(message = "Informe o modo de preparo da receita")
    private String modoPreparo;
    
    @Min(value = 1, message = "O tempo de preparo deve ser maior que zero")
    private int tempoPreparo;

    private String imagem;

    @NotNull(message = "Selecione a categoria da receita")
    private Long idCategoria;
    
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
