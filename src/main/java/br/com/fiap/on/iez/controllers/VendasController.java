package br.com.fiap.on.iez.controllers;

import br.com.fiap.on.iez.annotations.Permissao;
import br.com.fiap.on.iez.models.entities.dto.ClienteDTO;
import br.com.fiap.on.iez.models.entities.dto.ProdutoDTO;
import br.com.fiap.on.iez.models.entities.dto.VendasDTO;
import br.com.fiap.on.iez.services.VendasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
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
        List<VendasDTO> vendas = vendaService.listarTodasPaginado(pageable);

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

    @GetMapping("/export")
    @Permissao(rota = "exportarcsv")
    public ResponseEntity<Resource> exportarCsv() {
        List<VendasDTO> vendas = vendaService.listarTodas(); // Consulta integrando Produto e Cliente

        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("DataVenda,ID_Produto,NomeProduto,Categoria,PrecoUnitario,Quantidade,ID_Cliente,NomeCliente,TotalVenda\n");

        for (VendasDTO venda : vendas) {
            ProdutoDTO produto = venda.getProduto();
            ClienteDTO cliente = venda.getCliente();

            csvBuilder.append(venda.getDataVenda()).append(",")
                    .append(produto.getId()).append(",")
                    .append(produto.getNomeProduto()).append(",")
                    .append(produto.getCategoria().name()).append(",") // se for Enum
                    .append(produto.getPrecoUnitario()).append(",")
                    .append(venda.getQuantidade()).append(",")
                    .append(cliente.getId()).append(",")
                    .append(cliente.getNome()).append(",")
                    .append(venda.getTotalVenda())
                    .append("\n");
        }

        ByteArrayResource resource = new ByteArrayResource(csvBuilder.toString().getBytes(StandardCharsets.UTF_8));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(resource);
    }
}
