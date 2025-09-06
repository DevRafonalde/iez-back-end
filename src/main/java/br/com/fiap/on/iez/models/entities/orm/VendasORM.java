package br.com.fiap.on.iez.models.entities.orm;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "t_vendas", schema = "IEZ")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendasORM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "data_venda")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataVenda;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_produto", referencedColumnName = "id")
    private ProdutoORM produto;

    @Column(name = "quantidade")
    private Integer quantidade;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private ClienteORM cliente;

    @Column(name = "total_venda")
    private Double totalVenda;
}
