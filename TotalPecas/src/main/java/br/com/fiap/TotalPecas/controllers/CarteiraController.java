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
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/carteiras")
public class CarteiraController {
    

    Logger log = LoggerFactory.getLogger(CarteiraController.class);

    @Autowired
    CarteiraRepository repository; //Injeção de dependencia

    @GetMapping
    public List<Carteira> show(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Carteira> show(@PathVariable Long id){
        log.info("detalhando carteira" + id);
        var carteira = repository.findById(id).orElseThrow(() -> new RestNotFoundException("Carteira não encontrado"));

        return ResponseEntity.ok(carteira);
    }

    @PostMapping
    public ResponseEntity<Carteira> create(@RequestBody @Valid Carteira carteira, BindingResult result){
        log.info("cadastrando carteira" + carteira);
        repository.save(carteira);
        return ResponseEntity.status(HttpStatus.CREATED).body(carteira);
    }


    @PutMapping("{id}")
    public ResponseEntity<Carteira> update(@PathVariable Long id, @RequestBody @Valid Carteira carteira){
        log.info("atualizando carteira" + id);
        
        repository.findById(id).orElseThrow(() -> new RestNotFoundException("Erro ao atualizar, despesa não encontrada"));

        carteira.setId(id);
        repository.save(carteira);

        return ResponseEntity.ok(carteira);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Carteira> destroy(@PathVariable Long id){
        log.info("apagando carteira" + id);

        var carteira = repository.findById(id).orElseThrow(() -> new RestNotFoundException("Erro ao apagar, carteira não encontrado"));

        repository.delete(carteira);
        
        return ResponseEntity.noContent().build();    
    }

}
