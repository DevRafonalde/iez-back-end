package br.com.fiap.on.iez.controllers;

import br.com.fiap.on.iez.annotations.Permissao;
import br.com.fiap.on.iez.models.entities.dto.VendasDTO;
import br.com.fiap.on.iez.services.VendasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/vendas")
public class VendasController {
    @Autowired
    private VendasService vendaService;

    @PostMapping("/")
    @Permissao(rota = "cadastrarvenda")
    public ResponseEntity<VendasDTO> cadastrarVenda(@RequestBody @Valid VendasDTO vendaRecebida) {
        VendasDTO vendaCriada = vendaService.novaVenda(vendaRecebida);

        return new ResponseEntity<>(vendaCriada, HttpStatus.OK);
    }

    @GetMapping("/")
    @Permissao(rota = "listartodasvendas")
    public ResponseEntity<List<VendasDTO>> listarTodasVendas(Pageable pageable) {
        List<VendasDTO> vendas = vendaService.listarTodas(pageable);

        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Permissao(rota = "listarvendaespecifica")
    public ResponseEntity<VendasDTO> listarVendaEspecifica(@PathVariable Integer id) {
        VendasDTO vendaEncontrada = vendaService.listarPorId(id);

        return new ResponseEntity<>(vendaEncontrada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Permissao(rota = "deletarvenda")
    public ResponseEntity<Boolean> deletarVenda(@PathVariable Integer id) {
        vendaService.deletar(id);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Permissao(rota = "editarvenda")
    public ResponseEntity<VendasDTO> editarVenda(@PathVariable Integer id, @RequestBody @Valid VendasDTO vendaDTO) {
        VendasDTO vendaEditada = vendaService.editar(id, vendaDTO);

        return new ResponseEntity<>(vendaEditada, HttpStatus.OK);
    }
}
