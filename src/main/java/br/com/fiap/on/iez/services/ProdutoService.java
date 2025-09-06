package br.com.fiap.on.iez.services;

import br.com.fiap.on.iez.exceptions.ElementoNaoEncontradoException;
import br.com.fiap.on.iez.models.entities.dto.ProdutoDTO;
import br.com.fiap.on.iez.models.entities.orm.ProdutoORM;
import br.com.fiap.on.iez.models.repositories.ProdutoRepository;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtosRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private Validator validator;

    public List<ProdutoDTO> listarTodos(Pageable pageable) {
        Page<ProdutoORM> produtosBanco = produtosRepository.findAll(pageable);

        // Aqui é uma expressão lambda que passa por todos os itens, os tranforma em DTO e retorna uma lista de DTOs
        return produtosBanco.stream()
                .map(produto -> mapper.map(produto, ProdutoDTO.class))
                .toList();
    }

    public ProdutoDTO listarPorId(Integer id) {
        ProdutoORM produtoORM = produtosRepository.findById(id).orElseThrow(() -> new ElementoNaoEncontradoException("Não foi encontrado nenhum produto com os parâmetros enviados"));

        return mapper.map(produtoORM, ProdutoDTO.class);
    }

    public void deletar(int id) {
        ProdutoORM produtoDelete = produtosRepository.findById(id).orElseThrow(() -> new ElementoNaoEncontradoException("Produto não encontrado no banco de dados"));

        produtosRepository.delete(produtoDelete);
    }

    public ProdutoDTO novoProduto(ProdutoDTO produto) {
        ProdutoORM produtoRecebido = mapper.map(produto, ProdutoORM.class);
        ProdutoORM produtoCriado = produtosRepository.save(produtoRecebido);

        return mapper.map(produtoCriado, ProdutoDTO.class);
    }

    public ProdutoDTO editar(ProdutoDTO produto) {
        produtosRepository.findById(produto.getId()).orElseThrow(() -> new ElementoNaoEncontradoException("Produto não encontrado no banco de dados"));

        ProdutoORM produtoRecebido = mapper.map(produto, ProdutoORM.class);
        ProdutoORM produtoAtualizado = produtosRepository.save(produtoRecebido);

        return mapper.map(produtoAtualizado, ProdutoDTO.class);
    }
}
