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
public class ClienteDTO {

    private Integer id;

    @NotBlank(message = "O nome do cliente é obrigatório")
    private String nome;
}
