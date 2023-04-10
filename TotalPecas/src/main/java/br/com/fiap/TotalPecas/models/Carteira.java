package br.com.fiap.TotalPecas.models;

import java.math.BigDecimal;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carteira {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "Valor deve ser positivo") @NonNull
    private BigDecimal valor;

    @NotNull
    private BigDecimal saldo;
    
    @OneToOne
    private Usuario usuario;


    
    
}
