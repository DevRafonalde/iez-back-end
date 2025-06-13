package br.com.fiap.on.iez.services;

import br.com.fiap.on.iez.models.entities.orm.NumeroORM;
import br.com.fiap.on.iez.models.repositories.NumeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumeroService {
    @Autowired
    private NumeroRepository numeroRepository;

    /**
     * @param numeroRecebido Número a ser buscado no banco de dados
     * @return Retorna um objeto NumeroORM contendo as informações do número buscado
     */
    public NumeroORM buscarValor(Integer numeroRecebido) {
        return numeroRepository.findByValor(numeroRecebido);
    }

    public NumeroORM criarValor(NumeroORM valor) {
        return numeroRepository.save(valor);
    }
}
