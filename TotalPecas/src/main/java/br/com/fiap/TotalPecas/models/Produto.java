package br.com.fiap.TotalPecas.models;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Produto { // 
    private Long id;
    private BigDecimal preco;
    private String titulo;
    private ArrayList<Integer> anos;

 
    

    public Produto(BigDecimal preco, String titulo, ArrayList<Integer> anos) {
        this.preco = preco;
        this.titulo = titulo;
        this.anos = anos;
    }
    public BigDecimal getPreco() {
        return preco;
    }
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public ArrayList<Integer> getAnos() {
        return anos;
    }
    public void setAnos(ArrayList<Integer> anos) {
        this.anos = anos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Produto [preco=" + preco + ", titulo=" + titulo + ", anos=" + anos + "]";
    }

    
}
