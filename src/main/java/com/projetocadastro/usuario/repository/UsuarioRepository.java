package com.projetocadastro.usuario.repository;

import com.projetocadastro.usuario.entity.Usuario;

import java.lang.annotation.Target;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    Optional<Usuario> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);
}
