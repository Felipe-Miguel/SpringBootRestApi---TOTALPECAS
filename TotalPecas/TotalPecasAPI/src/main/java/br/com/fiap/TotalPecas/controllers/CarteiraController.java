package br.com.fiap.TotalPecas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.TotalPecas.models.Carteira;

@RestController
public class CarteiraController {
    

    Logger log = LoggerFactory.getLogger(CarteiraController.class);

    List<Carteira> lcarteiras = new ArrayList<>(); 

    @GetMapping("/api/carteiras")
    public List<Carteira> show(){
        return lcarteiras;
    }

    @GetMapping("/api/carteiras/{id}")
    public ResponseEntity<Carteira> show(@PathVariable Long id){
        log.info("detalhando carteira" + id);
        var carteiraEncontrada = lcarteiras.stream().filter(d -> d.getId().equals(id)).findFirst();

        if(carteiraEncontrada.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(carteiraEncontrada.get());
    }

    @PostMapping("/api/carteiras")
    public ResponseEntity<Carteira> create(@RequestBody Carteira carteira){
        log.info("cadastrando carteira" + carteira);
        carteira.setId(lcarteiras.size() + 1l);
        lcarteiras.add(carteira);
        return ResponseEntity.status(HttpStatus.CREATED).body(carteira);
    }


    @DeleteMapping("/api/carteiras/{id}")
    public ResponseEntity<Carteira> destroy(@PathVariable Long id){
        log.info("apagando carteira" + id);
        var carteiraEncontrada = lcarteiras.stream().filter(d -> d.getId().equals(id)).findFirst();
        if(carteiraEncontrada.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            lcarteiras.remove(carteiraEncontrada.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/api/carteiras/{id}")
    public ResponseEntity<Carteira> update(@PathVariable Long id, @RequestBody Carteira carteira){
        log.info("atualizando carteira" + id);
        var carteiraEncontrada = lcarteiras.stream().filter(d -> d.getId().equals(id)).findFirst();

        if(carteiraEncontrada.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
            lcarteiras.remove(carteiraEncontrada.get());
        carteira.setId(lcarteiras.size() + 1l);
        lcarteiras.add(carteira);
        return ResponseEntity.ok(carteira);
    }

}
