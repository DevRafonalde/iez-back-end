package br.com.fiap.on.iez.controllers;

import br.com.fiap.on.iez.models.entities.dto.ClienteDTO;
import br.com.fiap.on.iez.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/")
    public ResponseEntity<ClienteDTO> cadastrar(@RequestBody @Valid ClienteDTO clienteRecebido) {
        ClienteDTO clienteCriada = clienteService.novoCliente(clienteRecebido);

        return new ResponseEntity<>(clienteCriada, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ClienteDTO>> listar(Pageable pageable) {
        List<ClienteDTO> clientes = clienteService.listarTodos(pageable);

        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> listarEspecifico(@PathVariable Integer id) {
        ClienteDTO clienteEncontrado = clienteService.listarPorId(id);

        return new ResponseEntity<>(clienteEncontrado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletar(@PathVariable Integer id) {
        clienteService.deletar(id);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> editar(@PathVariable Integer id, @RequestBody @Valid ClienteDTO clienteDTO) {
        ClienteDTO clienteEditado = clienteService.editar(id, clienteDTO);

        return new ResponseEntity<>(clienteEditado, HttpStatus.OK);
    }
}
