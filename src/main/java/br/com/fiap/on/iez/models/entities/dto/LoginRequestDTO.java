package br.com.fiap.on.iez.models.entities.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotBlank(message = "Nome de usuário é obrigatório")
    private String nomeuUser;

    @NotBlank(message = "Senha é obrigatória")
    private String senha;
}
