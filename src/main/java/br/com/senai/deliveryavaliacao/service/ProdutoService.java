package br.com.senai.deliveryavaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.senai.deliveryavaliacao.dto.ProdutoRequest;
import br.com.senai.deliveryavaliacao.model.Produto;
import br.com.senai.deliveryavaliacao.model.Restaurante;
import br.com.senai.deliveryavaliacao.repository.ProdutoRepository;
import br.com.senai.deliveryavaliacao.repository.RestauranteRepository;

@Service 
public class ProdutoService {
     private final ProdutoRepository produtoRepository;
    private final RestauranteRepository restauranteRepository;

    public ProdutoService(
            ProdutoRepository produtoRepository,
            RestauranteRepository restauranteRepository) {

        this.produtoRepository = produtoRepository;
        this.restauranteRepository = restauranteRepository;
    }

    // Cadastrar equipamento
    public Optional<Produto> cadastrarProdutos(
            ProdutoRequest dados) {

        Restaurante restaurante = restauranteRepository.findById(dados.getRestauranteId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Restaurante não encontrado. Produto não cadastrado."
                ));

        Produto produto = new Produto();

        produto.setNome(dados.getNome());
        produto.setPreco(dados.getPreco());
        produto.setRestaurante(restaurante);

        return Optional.of(produtoRepository.save(produto));
    }

    // Listar equipamentos
    public List<Produto> listarProdutos() {

        return produtoRepository.findAll();
    }

    // Buscar equipamento por ID
    public Produto buscarProdutoPorId(Integer id) {

        return produtoRepository.findById(id).orElse(null);
    }

    // Atualizar equipamento
    public Optional<Produto> atualizarProduto(
            Integer id, ProdutoRequest dados) {

        Optional<Produto> produtoExistente =
                produtoRepository.findById(id);

        if (produtoExistente.isEmpty()) {
            return Optional.empty();
        }

        Restaurante restaurante = restauranteRepository.findById(dados.getRestauranteId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Restaurante não encontrado. produto não atualizado."
                ));

        Produto produto = produtoExistente.get();

        produto.setNome(dados.getNome());
        produto.setPreco(dados.getPreco());
        produto.setRestaurante(restaurante);

        return Optional.of(produtoRepository.save(produto));
    }

    // Deletar equipamento
    public boolean deletarProduto(Integer id) {

        Optional<Produto> produtoExistente =
                produtoRepository.findById(id);

        if (produtoExistente.isPresent()) {

            produtoRepository.deleteById(id);
            return true;
        }

        return false;
    }
}

