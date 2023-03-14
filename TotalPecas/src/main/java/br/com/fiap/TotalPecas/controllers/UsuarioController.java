package br.com.fiap.TotalPecas.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.TotalPecas.models.Usuario;

@RestController
public class UsuarioController {
    
    @GetMapping("/api/usuarios")
    public Usuario show(){
        Usuario usuario = new Usuario("Jose", "teste@gmail.com", "(11) 998572023", "47547896587", "47584370", "Av. Paulista", 200);
        return usuario;
    }
}
