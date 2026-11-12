package br.com.senai.deliveryavaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.senai.deliveryavaliacao.model.Restaurante;
import br.com.senai.deliveryavaliacao.repository.RestauranteRepository;
@Service 
public class RestauranteService {
    private final RestauranteRepository restauranteRepository;
     
    public RestauranteService(RestauranteRepository restauranteRepository){
        this.restauranteRepository = restauranteRepository;
    }
    public Restaurante cadastrarRestaurante(Restaurante restaurante ){
        return restauranteRepository.save(restaurante);
    }
    public List<Restaurante> listarRestaurante(){
        return restauranteRepository.findAll();
    }

    public Restaurante buscarRestaurantePorId(Integer id) {
        return restauranteRepository.findById(id).orElse(null);
    }
   public Optional<Restaurante> atualizarRestaurante(Integer id, Restaurante novosDados) {
        Optional<Restaurante> restauranteExistente = 
        restauranteRepository.findById(id);
        if (restauranteExistente.isPresent()) {
            Restaurante restaurante = restauranteExistente.get();
            restaurante.setNome(novosDados.getNome());
            return Optional.of(restauranteRepository.save(restaurante));
        } else {
            return Optional.empty();    
        }
    }

    public boolean deletarRestaurante(Integer id) {
        Optional<Restaurante> restauranteExistente = restauranteRepository.findById(id);
        if (restauranteExistente.isPresent()) {
            restauranteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    
}



