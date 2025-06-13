package br.com.fiap.on.iez.models.entities.orm;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_usuario_perfil")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioPerfilORM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private UsuarioORM usuario;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_perfil", referencedColumnName = "id")
    private PerfilORM perfil;

    @Column(name = "data_hora")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHora;
}