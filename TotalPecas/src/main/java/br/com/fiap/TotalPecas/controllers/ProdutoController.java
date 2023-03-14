package br.com.fiap.TotalPecas.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.TotalPecas.models.Produto;
import ch.qos.logback.classic.Logger;

@RestController
public class ProdutoController {  
    
    Logger log = LoggerFactory.getLogger(ProdutoController.class));
    List<Produto> lprodutos = new ArrayList<>(); 

    @GetMapping("/api/produtos")
    public Produto show(){
        Produto produto = new Produto(new BigDecimal(100), "teste de produto", new ArrayList<Integer>() {
            {
                add(2012);
                add(2013);
            }
        });
        return produto;
    }

    @PostMapping("/api/produtos")
    public void create(@RequestBody Produto produto){
        log.info("cadastrando produto" + produto);
        produto.setId(lprodutos.size() + 1l);
        lprodutos.add(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @GetMapping("api/produtos/{id}")
    public ResponseEntity<Produto> show(@PathVariable Long id){
        log.info("detalhando produto" + id);
        var produtoEncontrado = lprodutos.stream().filter(d -> d.getId().equals(id)).findFirst();

        if(produtoEncontrado.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(produtoEncontrado.get());
    }

    @DeleteMapping("api/produtos/{id}")
    public ResponseEntity<Produto> destroy(@PathVariable Long id){
        log.info("apagando produto" + id);
        var produtoEncontrado = lprodutos.stream().filter(d -> d.getId().equals(id)).findFirst();

        if(produtoEncontrado.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        lprodutos.remove(produtoEncontrado.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("api/produtos/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto produto){
        log.info("atualizando produto" + id);
        var produtoEncontrado = lprodutos.stream().filter(d -> d.getId().equals(id)).findFirst();

        if(produtoEncontrado.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        lprodutos.remove(produtoEncontrado.get());
        lprodutos.add(produto);
        return ResponseEntity.ok(produto);
    }
    

}
