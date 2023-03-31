package br.com.fiap.TotalPecas.models;

import java.math.BigDecimal;
import java.util.ArrayList;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity 
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "Valor deve ser positivo") @NonNull
    private BigDecimal preco;
    
    @NotBlank  @Size(min = 25, max = 125)
    private String titulo;

    @NotNull
    private ArrayList<Integer> anos;

    public Produto(BigDecimal preco, String titulo, ArrayList<Integer> anos) {
        this.preco = preco;
        this.titulo = titulo;
        this.anos = anos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Produto [preco=" + preco + ", titulo=" + titulo + ", anos=" + anos + "]";
    }

    
}
