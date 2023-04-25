package br.com.fiap.TotalPecas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.TotalPecas.exceptions.RestNotFoundException;
import br.com.fiap.TotalPecas.models.Carteira;
import br.com.fiap.TotalPecas.repository.CarteiraRepository;
import br.com.fiap.TotalPecas.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/carteiras")
public class CarteiraController {
    
    List<Carteira> carteiras = new ArrayList<>();


    Logger log = LoggerFactory.getLogger(CarteiraController.class);

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CarteiraRepository carteiraRepository; //Injeção de dependencia

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable){
        Page<Carteira> carteiras = (busca == null)?
        carteiraRepository.findAll(pageable):
        carteiraRepository.findBytituloContaining(busca, pageable);

    return assembler.toModel(carteiras.map(Carteira::toModel));
    }

    @GetMapping("{id}")
    public EntityModel<Carteira> show(@PathVariable Long id){
        log.info("detalhando carteira" + id);
        var carteira = getCarteira(id);
        return carteira.toModel();
    }   

    
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Carteira carteira, BindingResult result){
        log.info("cadastrando carteira" + carteira);
        carteiraRepository.save(carteira);
        carteira.setUsuario(usuarioRepository.findById(carteira.getUsuario().getId()).get());
        return ResponseEntity.created(carteira.toModel().getRequiredLink("self").toUri()).body(carteira.toModel());
    }
    

    @PutMapping("{id}")
    public EntityModel<Carteira> update(@PathVariable Long id, @RequestBody @Valid Carteira carteira){
        log.info("atualizando carteira" + id);
        getCarteira(id);
        carteira.setId(id);
        carteiraRepository.save(carteira);

        return carteira.toModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Carteira> destroy(@PathVariable Long id){
        log.info("apagando carteira" + id);
        var carteira = carteiraRepository.findById(id)
            .orElseThrow(() -> new RestNotFoundException("despesa não encontrada"));

        carteiraRepository.delete(carteira);

        return ResponseEntity.noContent().build();    
    }
    
    public Carteira getCarteira(Long id) {
        return carteiraRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Carteira não encontrada"));
    }
}
