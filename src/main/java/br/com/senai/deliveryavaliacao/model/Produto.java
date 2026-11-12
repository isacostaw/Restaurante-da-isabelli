package br.com.senai.deliveryavaliacao.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



@Entity 
@Table(name="produto")
public class Produto {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank (message="O nome do produto é obrigatório")
    private String nome;
  
    private BigDecimal preco;
    @ManyToOne 
    @JoinColumn(name="restaurante_id",nullable = false)
    @NotNull (message="O id do restaurante é obrigatorio")
    private Restaurante restaurante;

    public   Produto(){

    }
    public void setNome(String nome){
        this.nome=nome;
    }
    public void setPreco(BigDecimal preco){
        this.preco = preco;

    }
    public void setRestaurante(Restaurante restaurante){
        this.restaurante=restaurante;

    }
    
    public Restaurante getRestaurante(){
        return restaurante;
    }
    public BigDecimal getPreco(){
        return preco;
    }
    public String getNome(){
        return nome;
    }
    
   

}
