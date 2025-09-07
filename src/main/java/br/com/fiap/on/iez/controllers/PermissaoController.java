package br.com.fiap.on.iez.controllers;

import br.com.fiap.on.iez.annotations.Permissao;
import br.com.fiap.on.iez.models.entities.dto.PermissaoDTO;
import br.com.fiap.on.iez.services.PermissaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {
    @Autowired
    private PermissaoService permissaoService;

    @PostMapping("/cadastrar")
    @Permissao(rota = "cadastrarpermissao")
    public ResponseEntity<PermissaoDTO> cadastrarPermissao(@RequestBody @Valid PermissaoDTO permissaoRecebida) {
        PermissaoDTO permissaoCriada = permissaoService.novaPermissao(permissaoRecebida);

        return new ResponseEntity<>(permissaoCriada, HttpStatus.OK);
    }

    @GetMapping("/listar")
    @Permissao(rota = "listartodaspermissoes")
    public ResponseEntity<List<PermissaoDTO>> listarTodasPermissoes(Pageable pageable) {
        List<PermissaoDTO> permissoes = permissaoService.listarTodas(pageable);

        return new ResponseEntity<>(permissoes, HttpStatus.OK);
    }

    @GetMapping("/listar-especifico/{id}")
    @Permissao(rota = "listarpermissaoespecifica")
    public ResponseEntity<PermissaoDTO> listarPermissaoEspecifica(@PathVariable Integer id) {
        PermissaoDTO permissaoEncontrada = permissaoService.listarPorId(id);

        return new ResponseEntity<>(permissaoEncontrada, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    @Permissao(rota = "deletarpermissao")
    public ResponseEntity<Boolean> deletarPermissao(@PathVariable Integer id) {
        permissaoService.deletar(id);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/editar")
    @Permissao(rota = "editarpermissao")
    public ResponseEntity<PermissaoDTO> editarPermissao(@RequestBody @Valid PermissaoDTO permissaoDTO) {
        PermissaoDTO permissaoEditada = permissaoService.editar(permissaoDTO);

        return new ResponseEntity<>(permissaoEditada, HttpStatus.OK);
    }
}