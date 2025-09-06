package br.com.fiap.on.iez.models.repositories;

import br.com.fiap.on.iez.models.entities.orm.ProdutoORM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoORM, Integer> {
}
