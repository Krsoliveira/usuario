package com.projetocadastro.usuario.business.converter;

import com.projetocadastro.usuario.business.dto.EnderecoDTO;
import com.projetocadastro.usuario.business.dto.TelefoneDTO;
import com.projetocadastro.usuario.business.dto.UsuarioDTO;
import com.projetocadastro.usuario.entity.Endereco;
import com.projetocadastro.usuario.entity.Telefone;
import com.projetocadastro.usuario.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioConverter {

    /* =========================================
     *  DTO -> ENTITY
     * ========================================= */

    public Usuario paraUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) return null;
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefone(usuarioDTO.getTelefones()))
                .build();
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS) {
        if (enderecoDTOS == null) return Collections.emptyList();
        return enderecoDTOS.stream().map(this::paraEndereco).toList();
    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO) {
        if (enderecoDTO == null) return null;
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .complemento(enderecoDTO.getComplemento())
                .numero(enderecoDTO.getNumero())
                .build();
    }

    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDTOS) {
        if (telefoneDTOS == null) return Collections.emptyList();
        return telefoneDTOS.stream().map(this::paraTelefone).toList();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO) {
        if (telefoneDTO == null) return null;
        return Telefone.builder()
                .ddd(telefoneDTO.getDdd())
                .numero(telefoneDTO.getNumero())
                .build();
    }

    /* =========================================
     *  ENTITY -> DTO
     * ========================================= */

    public UsuarioDTO paraUsuarioDTO(Usuario usuario) {
        if (usuario == null) return null;
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(paraListaEnderecoDTO(usuario.getEnderecos()))
                .telefones(paraListaTelefoneDTO(usuario.getTelefones()))
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecos) {
        if (enderecos == null) return Collections.emptyList();
        return enderecos.stream().map(this::paraEnderecoDTO).toList();
    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco) {
        if (endereco == null) return null;
        return EnderecoDTO.builder()
                .rua(endereco.getRua())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .cep(endereco.getCep())
                .complemento(endereco.getComplemento())
                .numero(endereco.getNumero())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefones) {
        if (telefones == null) return Collections.emptyList();
        return telefones.stream().map(this::paraTelefoneDTO).toList();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefone) {
        if (telefone == null) return null;
        return TelefoneDTO.builder()
                .ddd(telefone.getDdd())
                .numero(telefone.getNumero())
                .build();
    }
}
