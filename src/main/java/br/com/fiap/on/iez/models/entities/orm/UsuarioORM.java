package br.com.fiap.on.iez.models.entities.orm;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_usuarios", schema = "IEZ")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(name = "nome_amigavel")
    private String nomeAmigavel;

    @Column(name = "nome_user")
    private String nomeUser;

    @Column(name = "senha_user")
    private String senhaUser;

    @Column(name = "senha_atualizada")
    private Boolean senhaAtualizada;

    @Column(name = "ativo")
    private Boolean ativo = true;
}