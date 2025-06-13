package br.com.fiap.on.iez.controllers;

import br.com.fiap.on.iez.models.entities.dto.PermissaoDTO;
import br.com.fiap.on.iez.services.PermissaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/permissoes")
public class PermissaoController {
    @Autowired
    private PermissaoService permissaoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<PermissaoDTO> cadastrar(@RequestBody @Valid PermissaoDTO permissaoRecebida) {
        PermissaoDTO permissaoCriada = permissaoService.novaPermissao(permissaoRecebida);

        return new ResponseEntity<>(permissaoCriada, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PermissaoDTO>> listar() {
        List<PermissaoDTO> permissoes = permissaoService.listarTodas();

        return new ResponseEntity<>(permissoes, HttpStatus.OK);
    }

    @GetMapping("/listar-especifico/{id}")
    public ResponseEntity<PermissaoDTO> listarEspecifico(@PathVariable Integer id) {
        PermissaoDTO permissaoEncontrada = permissaoService.listarPorId(id);

        return new ResponseEntity<>(permissaoEncontrada, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Boolean> deletar(@PathVariable Integer id) {
        permissaoService.deletar(id);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/editar")
    public ResponseEntity<PermissaoDTO> editar(@RequestBody @Valid PermissaoDTO permissaoDTO) {
        PermissaoDTO permissaoEditada = permissaoService.editar(permissaoDTO);

        return new ResponseEntity<>(permissaoEditada, HttpStatus.OK);
    }
}