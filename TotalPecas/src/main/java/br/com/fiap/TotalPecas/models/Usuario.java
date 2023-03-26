package br.com.fiap.TotalPecas.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Usuario {
    

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome, email, celular,cpf,cep,logradouro;
    private int numero;


    
    public Usuario(String nome, String email, String celular, String cpf, String cep, String logradouro, int numero) {
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.cpf = cpf;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    @Override
    public String toString() {
        return "Usuario [nome=" + nome + ", email=" + email + ", celular=" + celular + ", cpf=" + cpf + ", cep=" + cep
                + ", logradouro=" + logradouro + ", numero=" + numero + "]";
    }

    
}
