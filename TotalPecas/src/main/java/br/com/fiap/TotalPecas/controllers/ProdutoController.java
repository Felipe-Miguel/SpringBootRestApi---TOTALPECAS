package br.com.fiap.TotalPecas.controllers;


import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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


import br.com.fiap.TotalPecas.exceptions.RestNotFoundException;
import br.com.fiap.TotalPecas.models.RestValidationError;
import br.com.fiap.TotalPecas.models.Produto;
import br.com.fiap.TotalPecas.repository.ProdutoRepository;
import br.com.fiap.TotalPecas.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/produtos")
@Slf4j
public class ProdutoController {  
    
    @Autowired
    ProdutoRepository produtoRepository; //Injeção de dependencia

    @Autowired
    UsuarioRepository usuarioRepository; //Injeção de dependen  cia

    @GetMapping
    public Page<Produto> index(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable){
        if (busca == null) return produtoRepository.findAll(pageable);
        return produtoRepository.findByTituloContaining(busca, pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<Produto> show(@PathVariable Long id){
        log.info("detalhando produto com id" + id);
        return ResponseEntity.ok(getProduto(id));
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Produto produto, BindingResult result){
        
        log.info("cadastrando produto" + produto);
        produtoRepository.save(produto);
        produto.setUsuario(usuarioRepository.findById(produto.getUsuario().getId()).get());
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody @Valid Produto produto){
        
        log.info("atualizando produto" + id);
        
        getProduto(id);
        
        produto.setId(id);
        produtoRepository.save(produto);
        
        return ResponseEntity.ok(produto);
    }

    
    
    @DeleteMapping("{id}")
    public ResponseEntity<Produto> destroy(@PathVariable Long id){
        
        log.info("apagando produto" + id);
        
        produtoRepository.delete(getProduto(id));
        
        return ResponseEntity.noContent().build();
    
    }
    
    private Produto getProduto(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new RestNotFoundException("Erro ao atualizar, produto não encontrado"));
    }
}
