package br.edu.iff.ccc.appreceitas.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receita")
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receita")
    private Long idReceita;

    @Column(nullable = false)
    private String nome;

    @Lob
    @Column(name = "modo_preparo", nullable = false)
    private String modoPreparo;

    @Column(name = "tempo_preparo", nullable = false)
    private int tempoPreparo;

    private String imagem;

    @Column(name = "id_categoria", nullable = false)
    private Long idCategoria;

    @ElementCollection
    @CollectionTable(name = "receita_ingrediente", joinColumns = @JoinColumn(name = "id_receita"))
    @Column(name = "id_ingrediente")
    private List<Long> idsIngredientes = new ArrayList<>();

    public Receita(){
        
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
