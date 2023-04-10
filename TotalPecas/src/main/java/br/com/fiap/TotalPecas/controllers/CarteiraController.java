package br.com.fiap.TotalPecas.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.TotalPecas.exceptions.RestNotFoundException;
import br.com.fiap.TotalPecas.models.Carteira;
import br.com.fiap.TotalPecas.repository.CarteiraRepository;
import br.com.fiap.TotalPecas.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/carteiras")
public class CarteiraController {
    

    Logger log = LoggerFactory.getLogger(CarteiraController.class);

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CarteiraRepository carteiraRepository; //Injeção de dependencia

    @GetMapping
    public List<Carteira> show(){
        return carteiraRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Carteira> show(@PathVariable Long id){
        log.info("detalhando carteira" + id);
        return ResponseEntity.ok(getCarteira(id));
    }   

    
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Carteira carteira, BindingResult result){
        log.info("cadastrando carteira" + carteira);
        carteiraRepository.save(carteira);
        carteira.setUsuario(usuarioRepository.findById(carteira.getUsuario().getId()).get());
        return ResponseEntity.status(HttpStatus.CREATED).body(carteira);
    }
    

    @PutMapping("{id}")
    public ResponseEntity<Carteira> update(@PathVariable Long id, @RequestBody @Valid Carteira carteira){
        log.info("atualizando carteira" + id);
        getCarteira(id);
        carteira.setId(id);
        carteiraRepository.save(carteira);

        return ResponseEntity.ok(carteira);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Carteira> destroy(@PathVariable Long id){
        log.info("apagando carteira" + id);
        carteiraRepository.delete(getCarteira(id));
        return ResponseEntity.noContent().build();    
    }
    
    private Carteira getCarteira(Long id) {
        return carteiraRepository.findById(id).orElseThrow(() -> new RestNotFoundException("Carteira não encontrado"));
    }
}
