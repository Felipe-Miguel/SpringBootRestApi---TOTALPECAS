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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    private boolean ativo = true;
}