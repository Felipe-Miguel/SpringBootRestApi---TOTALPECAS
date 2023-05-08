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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import br.com.fiap.TotalPecas.models.Credencial;
import br.com.fiap.TotalPecas.models.Usuario;
import br.com.fiap.TotalPecas.repository.UsuarioRepository;
import br.com.fiap.TotalPecas.service.TokenJwtService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    Logger log = LoggerFactory.getLogger(UsuarioController.class);

    List<Usuario> usuarios = new ArrayList<>();

    @Autowired
    UsuarioRepository usuarioRepository; //Injeção de dependencia

    
    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @Autowired
    AuthenticationManager manager;
    
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenJwtService tokenJwtService;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable){
        Page<Usuario> usuarios = (busca == null)?
        usuarioRepository.findAll(pageable):
        usuarioRepository.findBynomeContaining(busca, pageable);

        return assembler.toModel(usuarios.map(Usuario::toEntityModel));
    }

    @PostMapping("/api/registrar")
    public ResponseEntity<Usuario> registrar(@RequestBody @Valid Usuario usuario){
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }


    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestBody @Valid Credencial credencial){
        manager.authenticate(credencial.toAuthentication());
        var token = tokenJwtService.generateToken(credencial);
        return ResponseEntity.ok(token);
    }

    @GetMapping("{id}")
    public EntityModel<Usuario> show(@PathVariable Long id){
        log.info("buscando despesa com id " + id);
        var usuario = getUsuario(id);
        
        return usuario.toEntityModel();
    }

    

    
    @DeleteMapping("{id}")
    public ResponseEntity<Usuario> destroy(@PathVariable Long id){
        log.info("apagando usuario" + id);
        var usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RestNotFoundException("despesa não encontrada"));
            
            usuario.setAtivo(false);
        usuarioRepository.save(usuario);
        
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public EntityModel<Usuario> update(@PathVariable Long id, @RequestBody @Valid Usuario usuario){
        log.info("atualizando usuario" + id);
        
        usuarioRepository.findById(id)
            .orElseThrow(() -> new RestNotFoundException("despesa não encontrada"));
        
        usuario.setId(id);

        usuarioRepository.save(usuario);

        return usuario.toEntityModel();
    }
    
    
    private Usuario getUsuario(Long id) {
        var usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Despesa não encontrada"));
        return usuario;
    }
    
}

