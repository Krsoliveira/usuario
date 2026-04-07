package com.projetocadastro.usuario.repository;

import com.projetocadastro.usuario.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndereçoRepository extends JpaRepository<Endereco, Long> {
}
