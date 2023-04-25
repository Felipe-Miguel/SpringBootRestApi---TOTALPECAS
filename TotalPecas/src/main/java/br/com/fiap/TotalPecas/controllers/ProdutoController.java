package br.com.fiap.TotalPecas.controllers;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import br.com.fiap.TotalPecas.models.Produto;
import br.com.fiap.TotalPecas.repository.ProdutoRepository;
import br.com.fiap.TotalPecas.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/produtos")
@Slf4j
public class ProdutoController {  

    List<Produto> produtos = new ArrayList<>();

    @Autowired
    ProdutoRepository produtoRepository; //Injeção de dependencia

    @Autowired
    UsuarioRepository usuarioRepository; //Injeção de dependen  cia


    @Autowired
    PagedResourcesAssembler<Object> assembler;


    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable){
        Page<Produto> produtos = (busca == null)?
            produtoRepository.findAll(pageable):
            produtoRepository.findBytituloContaining(busca, pageable);

        return assembler.toModel(produtos.map(Produto::toModel));
    }


    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Produto produto, BindingResult result){
        
        log.info("cadastrando produto" + produto);
        produtoRepository.save(produto);
        produto.setUsuario(usuarioRepository.findById(produto.getUsuario().getId()).get());
        return ResponseEntity
            .created(produto.toModel().getRequiredLink("self").toUri())
            .body(produto.toModel());
    }
    
    @PutMapping("{id}")
    public EntityModel<Produto> update(@PathVariable Long id, @RequestBody @Valid Produto produto){
        
        log.info("atualizando produto" + id);
        
        produtoRepository.findById(id)
            .orElseThrow(() -> new RestNotFoundException("carteira não encontrada"));
        
        produto.setId(id);
        produtoRepository.save(produto);
        
        return produto.toModel();
    }

    
    @GetMapping("{id}")
    public EntityModel<Produto> show(@PathVariable Long id){
        log.info("detalhando produto" + id);
        var produto = getProduto(id);
        return produto.toModel();
    }   


    @DeleteMapping("{id}")
    public ResponseEntity<Produto> destroy(@PathVariable Long id){
        
        log.info("apagando produto" + id);
        
        var produto = produtoRepository.findById(id)
            .orElseThrow(() -> new RestNotFoundException("despesa não encontrada"));
        
            produtoRepository.delete(produto);

            return ResponseEntity.noContent().build();
    
    }
    
    public Produto getProduto(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Produto não encontrada"));
    }
}
