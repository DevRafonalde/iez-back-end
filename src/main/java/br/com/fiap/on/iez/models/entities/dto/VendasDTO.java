package br.com.fiap.on.iez.models.entities.dto;

import br.com.fiap.on.iez.models.entities.orm.ClienteORM;
import br.com.fiap.on.iez.models.entities.orm.ProdutoORM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class VendasDTO {

    private Integer id;

    private LocalDate dataVenda;

    private ProdutoORM produto;

    private Integer quantidade;

    private ClienteORM cliente;

    private Double totalVenda;
}
