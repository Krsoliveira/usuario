package com.projetocadastro.usuario.business;

import com.projetocadastro.usuario.business.converter.UsuarioConverter;
import com.projetocadastro.usuario.business.dto.UsuarioDTO;
import com.projetocadastro.usuario.entity.Usuario;
import com.projetocadastro.usuario.infrastructure.exceptions.ResourseNotFoundException;
import com.projetocadastro.usuario.infrastructure.exceptions.conflictException;
import com.projetocadastro.usuario.repository.UsuarioRepository;
import com.projetocadastro.usuario.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {
        private final UsuarioRepository usuarioRepository;
        private final UsuarioConverter usuarioConverter;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;
        private final JwtUtil jwtUtil;

        public String loginUsuario(String email, String senha) {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(email, senha)
                );
                return jwtUtil.generateToken(email);
        }

        public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
                emailExiste(usuarioDTO.getEmail());
                usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
                Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
                return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
        }
        public void emailExiste(String email) {
                boolean existe = verificarEmailExistente(email);
                if (existe) {
                        throw new conflictException("Email já cadastrado: " + email);
                }
        }

        public boolean verificarEmailExistente(String email) {
                // Método que consulta o banco e retorna true ou false.

                return usuarioRepository.existsByEmail(email);
        }
        public Usuario buscarUsuarioPorEmail(String email) {
                return usuarioRepository.findByEmail(email)
                        .orElseThrow(() -> new ResourseNotFoundException("Email não encontrado: " + email));
        }
        public void deletarUsuarioPorEmail(String email) {
                buscarUsuarioPorEmail(email); // Verifica se o email existe antes de tentar deletar.
                usuarioRepository.deleteByEmail(email);
        }
        public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO dto) {
               String email = jwtUtil.extrairEmailToken(token.substring(7));
               dto.setSenha(dto.getSenha()!= null ? passwordEncoder.encode(dto.getSenha()) : null);
               Usuario usuarioEntity = usuarioRepository.findByEmail(email)
                       .orElseThrow(() -> new ResourseNotFoundException("Email não encontrado: " + email));

               Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);
               //usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

               return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
        }
}
