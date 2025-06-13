package br.com.fiap.on.iez.models.entities.orm;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "t_permissoes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PermissaoORM {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "ativo")
    private Boolean ativo = true;
}