package br.com.fiap.TotalPecas.models;

import java.math.BigDecimal;
import java.util.ArrayList;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "Valor deve ser positivo") @NonNull
    private BigDecimal preco;   
    
    @NotBlank  @Size(min = 25, max = 125, message = "O título deve conter entre 25 e 125 caracteres") @NotEmpty
    private String titulo;

    @NotNull
    private ArrayList<Integer> anos;

    @ManyToOne
    private Usuario usuario;
}
