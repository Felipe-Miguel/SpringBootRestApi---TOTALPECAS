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

import br.com.fiap.TotalPecas.models.Usuario;

@RestController
public class UsuarioController {
    
    Logger log = LoggerFactory.getLogger(CarteiraController.class);

    List<Usuario> lusuarios = new ArrayList<>(); 

    @GetMapping("/api/usuarios")
    public List<Usuario> show(){
        return lusuarios;
    }

    @PostMapping("/api/usuarios" )
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
        log.info("cadastrando usuario" + usuario);
        usuario.setId(lusuarios.size() + 1l);
        lusuarios.add(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping("/api/usuarios/{id}")
    public ResponseEntity<Usuario> show(@PathVariable Long id){
        log.info("detalhando usuario" + id);
        var usuarioEncontrado = lusuarios.stream().filter(d -> d.getId().equals(id)).findFirst();

        if(usuarioEncontrado.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(usuarioEncontrado.get());
    }

    @DeleteMapping("/api/usuarios/{id}")
    public ResponseEntity<Usuario> destroy(@PathVariable Long id){
        log.info("apagando usuario" + id);
        var usuarioEncontrado = lusuarios.stream().filter(d -> d.getId().equals(id)).findFirst();
        if(usuarioEncontrado.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            lusuarios.remove(usuarioEncontrado.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/api/usuario/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario){
        log.info("atualizando usuario" + id);
        var usuarioEncontrado = lusuarios.stream().filter(d -> d.getId().equals(id)).findFirst();

        if(usuarioEncontrado.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        lusuarios.remove(usuarioEncontrado.get());
        usuario.setId(lusuarios.size() + 1l);
        lusuarios.add(usuario);
        return ResponseEntity.ok(usuario);
    }
    

}

