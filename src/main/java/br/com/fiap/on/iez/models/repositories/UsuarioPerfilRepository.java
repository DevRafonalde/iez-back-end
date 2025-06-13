package br.com.fiap.on.iez.models.repositories;

import br.com.fiap.on.iez.models.entities.orm.PerfilORM;
import br.com.fiap.on.iez.models.entities.orm.UsuarioORM;
import br.com.fiap.on.iez.models.entities.orm.UsuarioPerfilORM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfilORM, Integer> {
    List<UsuarioPerfilORM> findByUsuario(UsuarioORM usuario);
    List<UsuarioPerfilORM> findByPerfil(PerfilORM perfil);
}
