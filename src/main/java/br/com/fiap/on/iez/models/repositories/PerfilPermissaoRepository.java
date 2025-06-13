package br.com.fiap.on.iez.models.repositories;

import br.com.fiap.on.iez.models.entities.orm.PerfilORM;
import br.com.fiap.on.iez.models.entities.orm.PerfilPermissaoORM;
import br.com.fiap.on.iez.models.entities.orm.PermissaoORM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerfilPermissaoRepository extends JpaRepository<PerfilPermissaoORM, Integer> {
    List<PerfilPermissaoORM> findByPerfil(PerfilORM perfil);
    List<PerfilPermissaoORM> findByPermissao(PermissaoORM permissao);
}
