package br.com.fiap.TotalPecas.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.TotalPecas.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
}
