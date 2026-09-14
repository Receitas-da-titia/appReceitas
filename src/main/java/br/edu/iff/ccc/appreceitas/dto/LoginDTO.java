package br.edu.iff.ccc.appreceitas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Credenciais utilizadas para autenticação do usuário")
public class LoginDTO {

    @NotBlank(message = "informe seu email")
    @Email(message = "informe um email válido")
    @Schema(description = "E-mail cadastrado", example = "maria@email.com")
    private String email;

    @NotBlank(message = "informe sua senha")
    @Schema(description = "Senha cadastrada", example = "senha123")
    private String senha;

    public LoginDTO() {
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
