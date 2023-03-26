package br.com.fiap.TotalPecas.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.TotalPecas.models.Carteira;
import br.com.fiap.TotalPecas.repository.CarteiraRepository;

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
        var carteiraEncontrada = repository.findById(id);

        if(carteiraEncontrada.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(carteiraEncontrada.get());
    }

    @PostMapping
    public ResponseEntity<Carteira> create(@RequestBody Carteira carteira){
        log.info("cadastrando carteira" + carteira);
        repository.save(carteira);
        return ResponseEntity.status(HttpStatus.CREATED).body(carteira);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Carteira> destroy(@PathVariable Long id){
        log.info("apagando carteira" + id);
        var carteiraEncontrada = repository.findById(id);
        if(carteiraEncontrada.isEmpty())
        return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Carteira> update(@PathVariable Long id, @RequestBody Carteira carteira){
        log.info("atualizando carteira" + id);
        var carteiraEncontrada = repository.findById(id);

        if(carteiraEncontrada.isEmpty())
        return ResponseEntity.notFound().build();
        
        carteira.setId(id);
        repository.save(carteira);
        return ResponseEntity.ok(carteira);
    }

}
