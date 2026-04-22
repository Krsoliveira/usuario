package com.projetocadastro.usuario.entity;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity // identifica a classe como uma entidade JPA
@Table(name = "Usuario") // nomeia a tabela
@Builder


public class Usuario implements UserDetails {

    @Id // gera a identidade da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // gera id único automaticamente
    private Long id;
    @Column(name = "Nome", length = 100, nullable = false)
    private String nome;
    @Column(name = "Email", length = 100, nullable = false)
    private String email;
    @Column(name = "Senha")
    private String senha;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Endereco> enderecos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Telefone> telefones;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

}
