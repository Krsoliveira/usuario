package com.projetocadastro.usuario.repository;

import com.projetocadastro.usuario.entity.Endereco;
import com.projetocadastro.usuario.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
