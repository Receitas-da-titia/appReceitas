package br.edu.iff.ccc.appreceitas.dto;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados necessários para cadastrar ou atualizar uma receita")
public class ReceitaDTO {

    @NotEmpty(message = "Receita deve ter pelo menos um ingrediente")
    @Schema(description = "Lista de IDs dos ingredientes utilizados na receita", example = "[1, 2, 3]")
    private List<Long> idsIngredientes = new ArrayList<>();

    @NotBlank(message = "Informe o nome da receita")
    @Schema(description = "Nome da receita", example = "Bolo de Cenoura")
    private String nome;

    @NotBlank(message = "Informe o modo de preparo da receita")
    @Schema(description = "Passo a passo do modo de preparo", example = "Misture todos os ingredientes e leve ao forno por 40 minutos.")
    private String modoPreparo;

    @Min(value = 1, message = "O tempo de preparo deve ser maior que zero")
    @Schema(description = "Tempo de preparo em minutos", example = "40", minimum = "1")
    private int tempoPreparo;

    @Schema(description = "URL ou nome do arquivo de imagem da receita", example = "bolo-cenoura.jpg")
    private String imagem;

    @NotNull(message = "Selecione a categoria da receita")
    @Schema(description = "ID da categoria à qual a receita pertence", example = "1")
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
