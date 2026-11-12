package br.com.senai.deliveryavaliacao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.deliveryavaliacao.dto.ProdutoRequest;
import br.com.senai.deliveryavaliacao.model.Produto;
import br.com.senai.deliveryavaliacao.service.ProdutoService;
import jakarta.validation.Valid;
@RestController 
@RequestMapping ("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // Deletar equipamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {

        boolean deletado = produtoService.deletarProduto(id);

        if (deletado) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    // Atualizar equipamento
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ProdutoRequest dados) {

        Optional<Produto> produtoAtualizado =
                produtoService.atualizarProduto(id, dados);

        if (produtoAtualizado.isPresent()) {
            return ResponseEntity.ok(produtoAtualizado.get());
        }

        return ResponseEntity.notFound().build();
    }

    // Buscar equipamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(
            @PathVariable Integer id) {

        Produto produto =
                produtoService.buscarProdutoPorId(id);

        if (produto != null) {
            return ResponseEntity.ok(produto);
        }

        return ResponseEntity.notFound().build();
    }

    // Listar equipamentos
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {

        List<Produto> produto =
                produtoService.listarProdutos();

        return ResponseEntity.ok(produto);
    }

    // Cadastrar equipamento
    @PostMapping
    public ResponseEntity<Produto> cadastrarProdutos(
            @Valid @RequestBody ProdutoRequest   dados) {

        Optional<Produto> produtoCadastrado =
                produtoService.cadastrarProdutos(dados);

        return produtoCadastrado
                .map(produto -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(produto))
                .orElse(ResponseEntity.notFound().build());
    }
}
    

