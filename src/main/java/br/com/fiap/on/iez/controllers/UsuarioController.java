package br.com.fiap.on.iez.controllers;

import br.com.fiap.on.iez.annotations.Permissao;
import br.com.fiap.on.iez.models.entities.dto.LoginRequestDTO;
import br.com.fiap.on.iez.models.entities.dto.LoginResponseDTO;
import br.com.fiap.on.iez.models.entities.dto.UsuarioDTO;
import br.com.fiap.on.iez.models.entities.dto.UsuarioPerfilDTO;
import br.com.fiap.on.iez.models.entities.orm.UsuarioORM;
import br.com.fiap.on.iez.services.JwtService;
import br.com.fiap.on.iez.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/cadastrar")
    @Permissao(rota = "cadastrarusuario")
    public ResponseEntity<UsuarioPerfilDTO> cadastrarUsuario(@RequestBody UsuarioPerfilDTO usuarioPerfilRecebido) {
        UsuarioPerfilDTO usuarioCadastrado = usuarioService.novoUsuario(usuarioPerfilRecebido);

        return new ResponseEntity<>(usuarioCadastrado, HttpStatus.OK);
    }

    @GetMapping("/listar")
    @Permissao(rota = "listartodosusuarios")
    public ResponseEntity<List<UsuarioDTO>> listarTodosUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.listarTodos();

        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/listar-especifico/{id}")
    @Permissao(rota = "listarusuarioespecifico")
    public ResponseEntity<UsuarioPerfilDTO> listarUsuarioEspecifico(@PathVariable Integer id) {
        UsuarioPerfilDTO usuario = usuarioService.listarEspecifico(id);

        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/clonar/{id}")
    @Permissao(rota = "clonarusuario")
    public ResponseEntity<UsuarioPerfilDTO> clonarUsuario(@PathVariable Integer id) {
        UsuarioPerfilDTO usuarioClonado = usuarioService.clonar(id);

        return new ResponseEntity<>(usuarioClonado, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    @Permissao(rota = "deletarusuario")
    public ResponseEntity<Boolean> deletarUsuario(@PathVariable Integer id) {
        usuarioService.deletar(id);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/editar")
    @Permissao(rota = "editarusuario")
    public ResponseEntity<UsuarioPerfilDTO> editarUsuario(@RequestBody @Valid UsuarioPerfilDTO modeloCadastroUsuarioPerfil) {
        UsuarioPerfilDTO usuarioEditado = usuarioService.editar(modeloCadastroUsuarioPerfil);

        return new ResponseEntity<>(usuarioEditado, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        UsuarioORM usuario = usuarioService.autenticar(loginRequest.getNomeUser(), loginRequest.getSenha());

        String token = jwtService.gerarToken(usuario);

        return ResponseEntity.ok(new LoginResponseDTO(usuario.getId(), usuario.getNomeAmigavel(), usuario.getNomeUser(), token));
    }
}
