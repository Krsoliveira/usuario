package com.projetocadastro.usuario.Controller;

import com.projetocadastro.usuario.business.UsuarioService;
import com.projetocadastro.usuario.business.converter.UsuarioConverter;
import com.projetocadastro.usuario.business.dto.UsuarioDTO;
import com.projetocadastro.usuario.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor

public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO) {
       return ResponseEntity.ok(usuarioService.salvar(usuarioDTO));
    }
}
