package com.projetocadastro.usuario.Controller;

import com.projetocadastro.usuario.business.UsuarioService;
import com.projetocadastro.usuario.business.dto.UsuarioDTO;
import com.projetocadastro.usuario.entity.Usuario;
import com.projetocadastro.usuario.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvar(usuarioDTO));
    }

    // ✅ DEIXE APENAS UM /login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioDTO usuarioDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usuarioDTO.getEmail(),
                        usuarioDTO.getSenha()
                )
        );

        // ✅ Retorne SÓ o token (sem "Bearer ")
        String token = jwtUtil.generateToken(authentication.getName());

        return ResponseEntity.ok(token);
    }

    @GetMapping
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }

    @DeleteMapping("/{email:.+}")
    public ResponseEntity<Void> deletarUsuarioPorEmail(@PathVariable String email) {
        usuarioService.deletarUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }
}