package br.com.fiap.on.iez.models.entities.orm;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_clientes", schema = "IEZ")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteORM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome_cliente")
    private String nomeCliente;
}
