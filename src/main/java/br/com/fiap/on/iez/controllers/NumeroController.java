package br.com.fiap.on.iez.controllers;

import br.com.fiap.on.iez.annotations.Permissao;
import br.com.fiap.on.iez.models.entities.orm.NumeroORM;
import br.com.fiap.on.iez.services.NumeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class NumeroController {
    @Autowired
    private NumeroService numeroService;

    @GetMapping("/{valor}")
    @Permissao(rota = "buscar-valor")
    public NumeroORM buscarValor(@PathVariable String valor) {
        Integer valorEmNumero = Integer.valueOf(valor);
        return numeroService.buscarValor(valorEmNumero);
    }

    @PostMapping("/criar-valor")
    public NumeroORM criarValor(@RequestBody NumeroORM valor) {
        return numeroService.criarValor(valor);
    }

}
