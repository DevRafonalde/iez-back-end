package br.com.fiap.on.iez.models.entities.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UsuarioDTO {

    private Integer id;

    @NotBlank(message = "Insira o nome completo do usuário")
    private String nomeCompleto;

    @NotBlank(message = "Insira um nome amigável para o usuário")
    private String nomeAmigavel;

    @NotBlank(message = "Insira o nome de usuário pelo qual o mesmo irá fazer login")
    private String nomeUser;

    private Boolean ativo = true;
}
