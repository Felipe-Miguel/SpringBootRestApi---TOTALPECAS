package br.com.fiap.TotalPecas.models;

import java.math.BigDecimal;

public class Carteira {
    private String conta_transferencia;
    private BigDecimal valor;
    private BigDecimal saldo;

    

    public Carteira(String conta_transferencia, BigDecimal valor, BigDecimal saldo) {
        this.conta_transferencia = conta_transferencia;
        this.valor = valor;
        this.saldo = saldo;
    }
    public String getConta_transferencia() {
        return conta_transferencia;
    }
    public void setConta_transferencia(String conta_transferencia) {
        this.conta_transferencia = conta_transferencia;
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
        return "Carteira [conta_transferencia=" + conta_transferencia + ", valor=" + valor + ", saldo=" + saldo + "]";
    }

    

}
