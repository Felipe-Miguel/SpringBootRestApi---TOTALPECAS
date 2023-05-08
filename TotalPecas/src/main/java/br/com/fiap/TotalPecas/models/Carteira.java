package br.com.fiap.TotalPecas.models;

import java.math.BigDecimal;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.TotalPecas.controllers.CarteiraController;
import br.com.fiap.TotalPecas.controllers.UsuarioController;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Entity 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carteira{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "Valor deve ser positivo") @NonNull
    private BigDecimal valor;

    @NotNull
    private BigDecimal saldo;
    
    @OneToOne
    private Usuario usuario;

    public EntityModel<Carteira> toEntityModel(){
        return EntityModel.of(
            this,
            linkTo(methodOn(CarteiraController.class).getCarteira(id)).withSelfRel(),
            linkTo(methodOn(CarteiraController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(CarteiraController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(UsuarioController.class).show(this.getUsuario().getId())).withRel("usuario")
        );
    }
    
    
}
