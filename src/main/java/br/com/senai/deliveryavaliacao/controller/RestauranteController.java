package br.com.senai.deliveryavaliacao.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.deliveryavaliacao.model.Restaurante;
import br.com.senai.deliveryavaliacao.service.RestauranteService;
import jakarta.validation.Valid;
@RestController 
@RequestMapping ("/restaurantes")
public class RestauranteController {
    

    private final RestauranteService restauranteService;
    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
        
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar
    (@Valid @PathVariable Integer id) {
        
        boolean deletado = restauranteService.deletarRestaurante(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @PutMapping("/{id}")
public ResponseEntity<Restaurante> atualizar(
       @Valid  @PathVariable Integer id,
        @RequestBody Restaurante novosDados) {

    if (novosDados.getNome() == null ||
            novosDados.getNome().isBlank()) {

        return ResponseEntity.badRequest().build();
    }

    return restauranteService.atualizarRestaurante(id, novosDados)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
}
        
        
    
    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarSetorPorId(@PathVariable Integer id) {
        Restaurante restaurante = restauranteService.buscarRestaurantePorId(id);
        if (restaurante != null) {
            return ResponseEntity.ok(restaurante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Restaurante>> listarSetores() {
        List<Restaurante> setores = restauranteService.listarRestaurante();
        return ResponseEntity.ok(setores);
    }
    
    @PostMapping
public ResponseEntity<Restaurante> cadastrarSetor(
        @Valid @RequestBody Restaurante restaurante) {

    return ResponseEntity.ok(restauranteService.cadastrarRestaurante(restaurante));
}
    






}

