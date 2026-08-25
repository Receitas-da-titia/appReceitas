package br.edu.iff.ccc.appreceitas.model;

import jakarta.persistence.*;


@Entity
@Table (name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)

public class Usuario {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column (name = "id_usuario")
private Long idUsuario;

@Column (name = "nome", nullable = false)
private String nome;

@Column (name = "email", nullable = false, unique = true)
    private String email;

@Column (name = "senha", nullable = false)
    private String senha;

    public Usuario() {
    }

    public Usuario(Long idUsuario, String nome, String email, String senha) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
