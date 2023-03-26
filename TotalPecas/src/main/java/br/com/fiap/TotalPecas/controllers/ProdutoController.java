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

import br.com.fiap.TotalPecas.models.Produto;
import br.com.fiap.TotalPecas.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {  
    
    Logger log = LoggerFactory.getLogger(ProdutoController.class);

    @Autowired
    ProdutoRepository repository; //Injeção de dependencia

    @GetMapping
    public List<Produto> show(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Produto> show(@PathVariable Long id){
        log.info("detalhando produto" + id);
        var produtoEncontrado = repository.findById(id);
        if(produtoEncontrado.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(produtoEncontrado.get());
    }

    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody Produto produto){
        log.info("cadastrando produto" + produto);
        repository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto produto){
        log.info("atualizando produto" + id);
        var produtoEncontrado = repository.findById(id);

        if(produtoEncontrado.isEmpty())
            return ResponseEntity.notFound().build();
        
        produto.setId(id);
        repository.save(produto);
        return ResponseEntity.ok(produto);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Produto> destroy(@PathVariable Long id){
        log.info("apagando produto" + id);
        var produtoEncontrado = repository.findById(id);
        if(produtoEncontrado.isEmpty())
            return ResponseEntity.notFound().build();
        repository.delete(produtoEncontrado.get());

        return ResponseEntity.noContent().build();
    }

    

}
