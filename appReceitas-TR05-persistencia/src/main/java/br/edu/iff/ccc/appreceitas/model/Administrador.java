package br.edu.iff.ccc.appreceitas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "administrador")
public class Administrador extends Usuario {
    public Administrador() { 
        super(); 
    }

    public Administrador(Long idUsuario, String nome, String email, String senha) {
        super(idUsuario, nome, email, senha);
    }
}