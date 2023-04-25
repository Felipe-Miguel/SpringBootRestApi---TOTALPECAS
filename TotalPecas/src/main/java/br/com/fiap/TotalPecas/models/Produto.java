package br.com.fiap.TotalPecas.models;

import java.math.BigDecimal;
import java.util.ArrayList;


import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.TotalPecas.controllers.ProdutoController;
import br.com.fiap.TotalPecas.controllers.UsuarioController;
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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
public class Produto extends EntityModel<Produto>{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "Valor deve ser positivo") @NonNull
    private BigDecimal preco;   
    
    @NotBlank  @Size(min = 25, max = 125, message = "O título deve conter entre 25 e 125 caracteres") @NotEmpty
    private String titulo;

    @NotNull
    private ArrayList<Integer> anos;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    public EntityModel<Produto> toModel(){
        return EntityModel.of(
            this,
            linkTo(methodOn(ProdutoController.class).getProduto(id)).withSelfRel(),
            linkTo(methodOn(ProdutoController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(ProdutoController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(UsuarioController.class).show(this.getUsuario().getId())).withRel("usuario")
        );
    }
}
