package br.com.fiap.TotalPecas.controllers;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.TotalPecas.models.Carteira;

@RestController
public class CarteiraController {
    
    @GetMapping("/api/carteiras")
    public Carteira show(){
        Carteira carteira = new Carteira("99958-3", new BigDecimal(250), new BigDecimal(1000));
        return carteira;
    }
}
