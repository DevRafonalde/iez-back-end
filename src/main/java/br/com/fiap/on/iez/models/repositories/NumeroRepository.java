package br.com.fiap.on.iez.models.repositories;

import br.com.fiap.on.iez.models.entities.orm.NumeroORM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumeroRepository extends JpaRepository<NumeroORM, Integer> {
    NumeroORM findByValor(Integer valor);
}
