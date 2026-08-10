package br.edu.iff.ccc.appreceitas.model;

public class Administrador extends Usuario {

    public Administrador() {
        super();
    }

    public Administrador(Long idUsuario, String nome, String email, String senha) {
        super(idUsuario, nome, email, senha);
    }
}
