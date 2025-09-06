package br.com.fiap.on.iez.services;

import br.com.fiap.on.iez.exceptions.ElementoNaoEncontradoException;
import br.com.fiap.on.iez.models.entities.dto.ClienteDTO;
import br.com.fiap.on.iez.models.entities.orm.ClienteORM;
import br.com.fiap.on.iez.models.repositories.ClienteRepository;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private Validator validator;

    public List<ClienteDTO> listarTodos(Pageable pageable) {
        Page<ClienteORM> clientesBanco = clienteRepository.findAll(pageable);

        // Aqui é uma expressão lambda que passa por todos os itens, os tranforma em DTO e retorna uma lista de DTOs
        return clientesBanco.stream()
                .map(cliente -> mapper.map(cliente, ClienteDTO.class))
                .toList();
    }

    public ClienteDTO listarPorId(Integer id) {
        ClienteORM clienteORM = clienteRepository.findById(id).orElseThrow(() -> new ElementoNaoEncontradoException("Não foi encontrado nenhum cliente com os parâmetros enviados"));

        return mapper.map(clienteORM, ClienteDTO.class);
    }

    public void deletar(int id) {
        ClienteORM clienteDelete = clienteRepository.findById(id).orElseThrow(() -> new ElementoNaoEncontradoException("Cliente não encontrado no banco de dados"));

        clienteRepository.delete(clienteDelete);
    }

    public ClienteDTO novoCliente(ClienteDTO cliente) {
        ClienteORM clienteRecebido = mapper.map(cliente, ClienteORM.class);
        ClienteORM clienteCriado = clienteRepository.save(clienteRecebido);

        return mapper.map(clienteCriado, ClienteDTO.class);
    }

    public ClienteDTO editar(Integer id, ClienteDTO cliente) {
        clienteRepository.findById(id).orElseThrow(() -> new ElementoNaoEncontradoException("Cliente não encontrado no banco de dados"));

        ClienteORM clienteRecebido = mapper.map(cliente, ClienteORM.class);
        ClienteORM clienteAtualizado = clienteRepository.save(clienteRecebido);

        return mapper.map(clienteAtualizado, ClienteDTO.class);
    }
}
