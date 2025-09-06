package br.com.fiap.on.iez.models.repositories;

import br.com.fiap.on.iez.models.entities.orm.ClienteORM;
import br.com.fiap.on.iez.models.entities.orm.ProdutoORM;
import br.com.fiap.on.iez.models.entities.orm.VendasORM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendasRepository extends JpaRepository<VendasORM, Integer> {
    List<VendasORM> findByProduto(ProdutoORM produto);
    List<VendasORM> findByCliente(ClienteORM cliente);
}
