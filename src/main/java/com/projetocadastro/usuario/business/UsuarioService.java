package com.projetocadastro.usuario.business;

import com.projetocadastro.usuario.business.converter.UsuarioConverter;
import com.projetocadastro.usuario.business.dto.UsuarioDTO;
import com.projetocadastro.usuario.entity.Usuario;
import com.projetocadastro.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {
        private final UsuarioRepository usuarioRepository;
        private final UsuarioConverter usuarioConverter;

        public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
                Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
                return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
        }

}
