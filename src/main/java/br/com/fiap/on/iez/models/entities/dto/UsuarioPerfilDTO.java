package br.com.fiap.on.iez.models.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPerfilDTO {

    private UsuarioDTO usuario;

    private List<PerfilDTO> perfisUsuario;
}
