package br.com.fiap.on.iez.models.entities.orm;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_perfis", schema = "IEZ")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PerfilORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "mnemonico")
    private String mnemonico;

    @Column(name = "ativo")
    private Boolean ativo = true;
}