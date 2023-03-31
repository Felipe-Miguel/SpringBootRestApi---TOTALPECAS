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

import br.com.fiap.TotalPecas.exceptions.RestNotFoundException;
import br.com.fiap.TotalPecas.models.Usuario;
import br.com.fiap.TotalPecas.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    UsuarioRepository repository; //Injeção de dependencia

    @GetMapping()
    public List<Usuario> show(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> show(@PathVariable Long id){
        log.info("detalhando usuario com id" + id);
        var usuario = repository.findById(id).orElseThrow(() -> new RestNotFoundException ("Usuario não encontrado"));
        return ResponseEntity.ok(usuario);
    }

    @PostMapping()
    public ResponseEntity<Usuario> create(@RequestBody @Valid Usuario usuario){
        log.info("cadastrando usuario" + usuario);
        repository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody @Valid Usuario usuario){
        log.info("atualizando usuario" + id);
        
        repository.findById(id).orElseThrow(() -> new RestNotFoundException("Erro ao atualizar, usuario não encontrado"));

        usuario.setId(id);
        repository.save(usuario);

        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Usuario> destroy(@PathVariable Long id){
        log.info("apagando usuario" + id);

        var usuario = repository.findById(id).orElseThrow(() -> new RestNotFoundException("Erro ao apagar, usuario não encontrado"));

        repository.delete(usuario);
        
        return ResponseEntity.noContent().build(); 
    }

    

}

