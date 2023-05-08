package br.com.fiap.TotalPecas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.TotalPecas.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    //@Query("SELECT p FROM Produto p WHERE p.Titulo LIKE %?1%")
    Page<Produto> findBytituloContaining(String busca, Pageable pageable);
    Page<Produto> findByprecoOrderByPrecoAsc(String busca, Pageable pageable);
    Page<Produto> findByprecoOrderByPrecoDesc(String busca, Pageable pageable);

    //@Query("SELECT p FROM Produto p ORDER BY p.preco")
    //Page<Produto> findByPreco (String busca, Pageable pageable)
    //@Query("SELECT p FROM Produto p ORDER BY p.id LIMIT ?1 OFFSET ?2")
    //List<Produto> buscarPaginado(int tamanho, int offset);
}
