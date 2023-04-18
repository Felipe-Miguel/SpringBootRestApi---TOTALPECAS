package br.com.fiap.TotalPecas.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.TotalPecas.models.Carteira;
import br.com.fiap.TotalPecas.models.Produto;
import br.com.fiap.TotalPecas.models.Usuario;
import br.com.fiap.TotalPecas.repository.CarteiraRepository;
import br.com.fiap.TotalPecas.repository.ProdutoRepository;
import br.com.fiap.TotalPecas.repository.UsuarioRepository;

@Configuration
public class DataBaseSeeder implements CommandLineRunner {

    @Autowired
    UsuarioRepository usuarioRepository;                          
    
    @Autowired
    CarteiraRepository carteiraRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    
@Override
public void run(String... args) throws Exception {
    Usuario u1 = new Usuario(1L, "Usuario1", "199999999", "19999999999", "199999990", "Rua1", "usuario1@gmail.com", 55, true);
    Usuario u2 = new Usuario(2L, "Usuario2", "299999999", "29999999999", "299999990", "Rua2", "usuario2@gmail.com", 55, true);
    Usuario u3 = new Usuario(3L, "Usuario3", "399999999", "39999999999", "399999990", "Rua3", "usuario3@gmail.com", 55, true);
    

    usuarioRepository.saveAll(List.of(u1, u2, u3));
    
    carteiraRepository.saveAll(List.of(
        new Carteira(1L, new BigDecimal(199.99), new BigDecimal(199.99), u1),
        new Carteira(2L, new BigDecimal(99), new BigDecimal(99), u2),
        new Carteira(3L, new BigDecimal(299.99), new BigDecimal(299.99), u3)
    ));


    ArrayList<Integer> listaAnos1 = new ArrayList<Integer>(){{
        add(2012);
        add(2013);
        add(2014);
    }};

    
    produtoRepository.saveAll(List.of(
        new Produto(1L, new BigDecimal(199.99), "Produto 1 Lorem Lorem Lorem Lorem ", listaAnos1, u1),
        new Produto(2L, new BigDecimal(150.49), "Produto 2 Lorem Lorem Lorem Lorem ", listaAnos1, u2),
        new Produto(3L, new BigDecimal(180.49), "Produto 3 Lorem Lorem Lorem Lorem ", listaAnos1, u3)
    ));
}
}
