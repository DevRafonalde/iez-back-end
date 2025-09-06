package br.com.fiap.on.iez.services;

import br.com.fiap.on.iez.exceptions.ElementoNaoEncontradoException;
import br.com.fiap.on.iez.models.entities.dto.VendasDTO;
import br.com.fiap.on.iez.models.entities.orm.VendasORM;
import br.com.fiap.on.iez.models.repositories.VendasRepository;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendasService {
    @Autowired
    private VendasRepository vendasRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private Validator validator;

    public List<VendasDTO> listarTodos(Pageable pageable) {
        Page<VendasORM> vendasBanco = vendasRepository.findAll(pageable);

        // Aqui é uma expressão lambda que passa por todos os itens, os tranforma em DTO e retorna uma lista de DTOs
        return vendasBanco.stream()
                .map(venda -> mapper.map(venda, VendasDTO.class))
                .toList();
    }

    public VendasDTO listarPorId(Integer id) {
        VendasORM vendaORM = vendasRepository.findById(id).orElseThrow(() -> new ElementoNaoEncontradoException("Não foi encontrada nenhuma venda com os parâmetros enviados"));

        return mapper.map(vendaORM, VendasDTO.class);
    }

    public void deletar(int id) {
        VendasORM vendaDelete = vendasRepository.findById(id).orElseThrow(() -> new ElementoNaoEncontradoException("Venda não encontrada no banco de dados"));

        vendasRepository.delete(vendaDelete);
    }

    public VendasDTO novoVendas(VendasDTO venda) {
        VendasORM vendaRecebida = mapper.map(venda, VendasORM.class);
        VendasORM vendaCriada = vendasRepository.save(vendaRecebida);

        return mapper.map(vendaCriada, VendasDTO.class);
    }

    public VendasDTO editar(VendasDTO venda) {
        vendasRepository.findById(venda.getId()).orElseThrow(() -> new ElementoNaoEncontradoException("Venda não encontrada no banco de dados"));

        VendasORM vendaRecebida = mapper.map(venda, VendasORM.class);
        VendasORM vendaAtualizada = vendasRepository.save(vendaRecebida);

        return mapper.map(vendaAtualizada, VendasDTO.class);
    }
}