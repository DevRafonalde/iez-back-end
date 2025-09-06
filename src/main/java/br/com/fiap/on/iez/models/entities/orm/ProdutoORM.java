package br.com.fiap.on.iez.models.entities.orm;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_produtos", schema = "IEZ")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoORM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome_produto")
    private String nomeProduto;

    @Column(name = "preco_unitario")
    private Integer precoUnitario;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private CategoriasORM categoria;
}
