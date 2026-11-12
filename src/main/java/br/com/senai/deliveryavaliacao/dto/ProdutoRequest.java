package br.com.senai.deliveryavaliacao.dto;

import java.math.BigDecimal;


public class ProdutoRequest {
    private String nome;
    private BigDecimal preco;
    private Integer restauranteId;

    public ProdutoRequest(){

    }
    public void setPreco(BigDecimal preco){
        this.preco=preco;
    }
    public void setNome(String nome ){
        this.nome=nome;
    }
    public void setRestauranteId(Integer restauranteId){
        this.restauranteId=restauranteId;

    }
    public String getNome(){
        return nome;
    }
    public BigDecimal getPreco(){
        return preco;
    }
    public Integer getRestauranteId(){
        return restauranteId;
    }
}
