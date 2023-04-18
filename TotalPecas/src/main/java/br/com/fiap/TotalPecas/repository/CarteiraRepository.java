package br.com.fiap.TotalPecas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.TotalPecas.models.Carteira;

public interface CarteiraRepository extends JpaRepository<Carteira, Long>{
}