package br.com.fiap.on.iez.models.repositories;

import br.com.fiap.on.iez.models.entities.orm.UsuarioORM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<UsuarioORM, Integer> {
    List<UsuarioORM> findByNomeAmigavelContaining(String nomeAmigavel);
    UsuarioORM findByNomeUser(String nomeUser);
}
