package com.projetocadastro.usuario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity // identifica a classe como uma entidade JPA
@Table(name = "endereco")// nomeia a tabela
public class Endereco {
        @Id // Identificador único da tabela
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "rua", length = 100, nullable = false)
        private String rua;
        @Column(name = "cidade", length = 100, nullable = false)
        private String cidade;
        @Column(name = "estado", length = 2, nullable = false)
        private String estado;
        @Column(name = "cep", length = 9, nullable = false)
        private String cep;
        @Column(name = "complemento", length = 100, nullable = false)
        private String complemento;
        @Column(name = "numero", length = 100, nullable = false)
        private Long numero;
        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "usuario_id", referencedColumnName = "id")
        private List<Telefone> telefones;
    }
