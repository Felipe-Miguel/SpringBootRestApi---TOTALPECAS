package br.com.fiap.TotalPecas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.TotalPecas.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
