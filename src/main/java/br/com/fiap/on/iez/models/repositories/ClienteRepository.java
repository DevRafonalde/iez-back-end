package br.com.fiap.on.iez.models.repositories;

import br.com.fiap.on.iez.models.entities.orm.ClienteORM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteORM, Integer> {
}
