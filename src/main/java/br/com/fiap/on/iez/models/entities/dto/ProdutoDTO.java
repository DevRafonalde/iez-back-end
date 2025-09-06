package br.com.fiap.on.iez.models.entities.dto;

import br.com.fiap.on.iez.models.entities.orm.CategoriasORM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ProdutoDTO {

    private Integer id;

    private String nomeProduto;

    private Integer precoUnitario;

    private CategoriasORM categoria;
}
