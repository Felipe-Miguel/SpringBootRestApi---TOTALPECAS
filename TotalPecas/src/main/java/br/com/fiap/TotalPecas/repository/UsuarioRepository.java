package br.com.fiap.TotalPecas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.fiap.TotalPecas.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Page<Usuario> findBynomeContaining(String busca, Pageable pageable);
}
