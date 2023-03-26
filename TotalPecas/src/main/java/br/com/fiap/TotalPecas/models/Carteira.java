package br.com.fiap.TotalPecas.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity 
public class Carteira {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private BigDecimal valor;
    private BigDecimal saldo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Carteira(String conta_transferencia, BigDecimal valor, BigDecimal saldo) {
        this.valor = valor;
        this.saldo = saldo;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public BigDecimal getSaldo() {
        return saldo;
    }
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
    @Override
    public String toString() {
        return "Carteira " + ", valor=" + valor + ", saldo=" + saldo + "]";
    }

    

}
