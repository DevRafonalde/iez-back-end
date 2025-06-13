package br.com.fiap.on.iez.models.entities.orm;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_perfil_permissao")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PerfilPermissaoORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_perfil", referencedColumnName = "id")
    private PerfilORM perfil;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_permissao", referencedColumnName = "id")
    private PermissaoORM permissao;

    @Column(name = "data_hora")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHora;
}
