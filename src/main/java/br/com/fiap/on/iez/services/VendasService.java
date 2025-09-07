package br.com.fiap.on.iez.services;

import br.com.fiap.on.iez.exceptions.ElementoNaoEncontradoException;
import br.com.fiap.on.iez.models.entities.dto.VendasDTO;
import br.com.fiap.on.iez.models.entities.orm.ProdutoORM;
import br.com.fiap.on.iez.models.entities.orm.VendasORM;
import br.com.fiap.on.iez.models.repositories.ClienteRepository;
import br.com.fiap.on.iez.models.repositories.ProdutoRepository;
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
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private Validator validator;

    public List<VendasDTO> listarTodasPaginado(Pageable pageable) {
        Page<VendasORM> vendasBanco = vendasRepository.findAll(pageable);

        // Aqui é uma expressão lambda que passa por todos os itens, os tranforma em DTO e retorna uma lista de DTOs
        return vendasBanco.stream()
                .map(venda -> mapper.map(venda, VendasDTO.class))
                .toList();
    }

    public List<VendasDTO> listarTodas() {
        List<VendasORM> vendasBanco = vendasRepository.findAll();

        // Aqui é uma expressão lambda que passa por todos os itens, os tranforma em DTO e retorna uma lista de DTOs
        List<VendasDTO> vendasDto =  vendasBanco.stream()
                .map(venda -> mapper.map(venda, VendasDTO.class))
                .toList();

        return vendasBanco.stream()
                .map(venda -> mapper.map(venda, VendasDTO.class))
                .toList();
//        for (VendasDTO venda : vendasDto) {
//            ProdutoORM produtoBanco = produtoRepository.findById(venda.get)
//        }
    }

    public VendasDTO listarPorId(Integer id) {
        VendasORM vendaORM = vendasRepository.findById(id).orElseThrow(() -> new ElementoNaoEncontradoException("Não foi encontrada nenhuma venda com os parâmetros enviados"));

        return mapper.map(vendaORM, VendasDTO.class);
    }

    public void deletar(int id) {
        VendasORM vendaDelete = vendasRepository.findById(id).orElseThrow(() -> new ElementoNaoEncontradoException("Venda não encontrada no banco de dados"));

        vendasRepository.delete(vendaDelete);
    }

    public VendasDTO novaVenda(VendasDTO venda) {
        VendasORM vendaRecebida = mapper.map(venda, VendasORM.class);
        VendasORM vendaCriada = vendasRepository.save(vendaRecebida);

        return mapper.map(vendaCriada, VendasDTO.class);
    }

    public VendasDTO editar(Integer id, VendasDTO venda) {
        vendasRepository.findById(id).orElseThrow(() -> new ElementoNaoEncontradoException("Venda não encontrada no banco de dados"));

        VendasORM vendaRecebida = mapper.map(venda, VendasORM.class);
        VendasORM vendaAtualizada = vendasRepository.save(vendaRecebida);

        return mapper.map(vendaAtualizada, VendasDTO.class);
    }
}