package br.com.fiap.on.iez.controllers;

import br.com.fiap.on.iez.annotations.Permissao;
import br.com.fiap.on.iez.models.entities.dto.ProdutoDTO;
import br.com.fiap.on.iez.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/")
    @Permissao(rota = "cadastrarproduto")
    public ResponseEntity<ProdutoDTO> cadastrarProduto(@RequestBody @Valid ProdutoDTO produtoRecebido) {
        ProdutoDTO produtoCriada = produtoService.novoProduto(produtoRecebido);

        return new ResponseEntity<>(produtoCriada, HttpStatus.OK);
    }

    @GetMapping("/")
    @Permissao(rota = "listartodosprodutos")
    public ResponseEntity<List<ProdutoDTO>> listarTodosProdutos(Pageable pageable) {
        List<ProdutoDTO> produtos = produtoService.listarTodos(pageable);

        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Permissao(rota = "listarprodutoespecifico")
    public ResponseEntity<ProdutoDTO> listarProdutoEspecifico(@PathVariable Integer id) {
        ProdutoDTO produtoEncontrado = produtoService.listarPorId(id);

        return new ResponseEntity<>(produtoEncontrado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Permissao(rota = "deletarproduto")
    public ResponseEntity<Boolean> deletarProduto(@PathVariable Integer id) {
        produtoService.deletar(id);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Permissao(rota = "editarproduto")
    public ResponseEntity<ProdutoDTO> editarProduto(@PathVariable Integer id, @RequestBody @Valid ProdutoDTO produtoDTO) {
        ProdutoDTO produtoEditado = produtoService.editar(id, produtoDTO);

        return new ResponseEntity<>(produtoEditado, HttpStatus.OK);
    }
}
