package br.com.fiap.on.iez.models.entities.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilPermissaoDTO {
    private PerfilDTO perfil;

    private List<PermissaoDTO> permissoes;
}
