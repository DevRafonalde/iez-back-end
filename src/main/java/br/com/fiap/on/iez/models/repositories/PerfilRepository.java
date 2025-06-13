package br.com.fiap.on.iez.models.repositories;

import br.com.fiap.on.iez.models.entities.orm.PerfilORM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerfilRepository extends JpaRepository<PerfilORM, Integer> {
    List<PerfilORM> findByNomeContaining(String nome);
    PerfilORM findByMnemonico(String mnemonico);
}
