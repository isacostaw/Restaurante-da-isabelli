package br.com.senai.deliveryavaliacao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity 
@Table (name="restaurante")
public class Restaurante{
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank (message = "O nome do restaurante é obriagatório")
    String nome;
    @NotBlank (message = "O endereço do restaurante é obrigatorio")
    String endereco;

    public Restaurante(){

    }
    
    public Integer getId(){
        return id;
    }
    public String getNome(){
        return nome;

    }
    public String getEndereco(){
        return endereco;
    }
    public void setNome(String nome ){
        this.nome= nome ;


    }
    public void setEndereco(String endereco){
        this.endereco=endereco;
    }
}