package br.edu.iff.ccc.appreceitas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados necessários para cadastrar um usuário")
public class UsuarioDTO {

    @NotBlank(message = "Informe o nome do usuário")
    @Schema(description = "Nome completo do usuário", example = "Maria Silva")
    private String nome;

    @NotBlank(message = "Informe o email do usuário")
    @Email(message = "Informe um email válido")
    @Schema(description = "E-mail do usuário, utilizado para login", example = "maria@email.com")
    private String email;

    @NotBlank(message = "Informe a senha do usuário")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    @Schema(description = "Senha de acesso, mínimo de 6 caracteres", example = "senha123", minLength = 6)
    private String senha;

    public UsuarioDTO() {
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
