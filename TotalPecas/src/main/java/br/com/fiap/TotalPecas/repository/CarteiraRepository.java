package br.com.fiap.TotalPecas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.TotalPecas.models.Carteira;

public interface CarteiraRepository extends JpaRepository<Carteira, Long>{
    Page<Carteira> findBytituloContaining(String busca, org.springframework.data.domain.Pageable pageable);
}