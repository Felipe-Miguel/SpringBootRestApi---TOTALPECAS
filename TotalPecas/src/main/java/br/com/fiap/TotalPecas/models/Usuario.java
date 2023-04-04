package br.com.fiap.TotalPecas.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity 
public class Usuario {
    

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank  @Size(min = 4, max = 70, message = "O nome deve conter ao menos 4 letras") @NotEmpty
    private String nome;
    
    @NotBlank  @Size(min = 9, max = 9, message = "O celular deve conter no minímo e até 9 caracteres e devem ser numéricos") @NotEmpty
    private String celular;
    
    @NotBlank  @Size(min = 11, max = 11, message = "O CPF deve conter no minímo e até 11 caracteres e devem ser numéricos") @NotEmpty   
    private String cpf;
    

    @NotBlank  @Size(min = 9, max = 9, message = "O CEP deve conter no minímo e até 9 caracteres e devem ser numéricos")  @NotEmpty
    private String cep;
    
    @NotBlank  @Size(min = 1, max = 120, message ="O logradouro deve conter minímo 1 caracter e no maximo 120 caracteres")   
    private String logradouro;

    @Email
    @NotBlank(message = "Insira um email válido")
    private String email;
    
    @NotBlank  @Size(min = 1, max = 6)
    @Min(value = 1, message = "O número da residência deve ser maior que 0")  
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
