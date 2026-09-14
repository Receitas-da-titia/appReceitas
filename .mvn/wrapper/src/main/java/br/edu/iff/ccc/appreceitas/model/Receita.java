package br.edu.iff.ccc.appreceitas.model;


public class Receita {

    private Long id;
    private String nome;
    private String ingredientes;
    private String modoPreparo;
    private String categoria;
    private Integer tempoPreparoMinutos;
    private String imagemUrl;

    public Receita() {
    }

    public Receita(Long id, String nome, String ingredientes, String modoPreparo,
                    String categoria, Integer tempoPreparoMinutos, String imagemUrl) {
        this.id = id;
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.modoPreparo = modoPreparo;
        this.categoria = categoria;
        this.tempoPreparoMinutos = tempoPreparoMinutos;
        this.imagemUrl = imagemUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(String modoPreparo) {
        this.modoPreparo = modoPreparo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getTempoPreparoMinutos() {
        return tempoPreparoMinutos;
    }

    public void setTempoPreparoMinutos(Integer tempoPreparoMinutos) {
        this.tempoPreparoMinutos = tempoPreparoMinutos;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }
}
