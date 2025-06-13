package br.com.fiap.on.iez.models.repositories;

import br.com.fiap.on.iez.models.entities.orm.PermissaoORM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<PermissaoORM, Integer> {
}
