package br.com.fiap.on.iez.controllers;

import br.com.fiap.on.iez.models.entities.dto.PerfilDTO;
import br.com.fiap.on.iez.models.entities.dto.UsuarioDTO;
import br.com.fiap.on.iez.models.entities.dto.UsuarioPerfilDTO;
import br.com.fiap.on.iez.services.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioPerfilDTO> cadastrar(UsuarioPerfilDTO usuarioPerfilRecebido) {
        UsuarioPerfilDTO usuarioCadastrado = usuarioService.novoUsuario(usuarioPerfilRecebido);

        return new ResponseEntity<>(usuarioCadastrado, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTO>> listar() {
        List<UsuarioDTO> usuarios = usuarioService.listarTodos();

        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/listar-especifico/{id}")
    public ResponseEntity<UsuarioPerfilDTO> listarEspecifico(@PathVariable Integer id) {
        UsuarioPerfilDTO usuario = usuarioService.listarEspecifico(id);

        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/clonar/{id}")
    public ResponseEntity<UsuarioPerfilDTO> clonar(@PathVariable Integer id) {
        UsuarioPerfilDTO usuarioClonado = usuarioService.clonar(id);

        return new ResponseEntity<>(usuarioClonado, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Boolean> deletar(@PathVariable Integer id) {
        usuarioService.deletar(id);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/editar")
    public ResponseEntity<UsuarioPerfilDTO> editar(@Valid UsuarioPerfilDTO modeloCadastroUsuarioPerfil) {
        UsuarioPerfilDTO usuarioEditado = usuarioService.editar(modeloCadastroUsuarioPerfil);

        return new ResponseEntity<>(usuarioEditado, HttpStatus.OK);
    }

    @RequestMapping(value="/novo-usuario", params={"addPerfil"})
    public String addRowCadastro(final UsuarioPerfilDTO modeloCadastroUsuarioPerfil, final BindingResult bindingResult) {
        if (Objects.isNull(modeloCadastroUsuarioPerfil.getPerfisUsuario())) {
            modeloCadastroUsuarioPerfil.setPerfisUsuario(new ArrayList<>());
        }
        modeloCadastroUsuarioPerfil.getPerfisUsuario().add(0, new PerfilDTO());
        return "usuarios/cadastro";
    }

    @RequestMapping(value="/novo-usuario", params={"removePerfil"})
    public String removeRowCadastro(final UsuarioPerfilDTO modeloCadastroUsuarioPerfil, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer perfilUsuarioId = Integer.valueOf(req.getParameter("removePerfil"));
        modeloCadastroUsuarioPerfil.getPerfisUsuario().remove(perfilUsuarioId.intValue());
        return "usuarios/cadastro";
    }

    @RequestMapping(value="/editar", params={"addPerfil"})
    public String addRowEdicao(final UsuarioPerfilDTO modeloCadastroUsuarioPerfil, final BindingResult bindingResult) {
        if (Objects.isNull(modeloCadastroUsuarioPerfil.getPerfisUsuario())) {
            modeloCadastroUsuarioPerfil.setPerfisUsuario(new ArrayList<>());
        }
        modeloCadastroUsuarioPerfil.getPerfisUsuario().add(0, new PerfilDTO());
        return "usuarios/edicao";
    }

    @RequestMapping(value="/editar", params={"removePerfil"})
    public String removeRowEdicao(final UsuarioPerfilDTO modeloCadastroUsuarioPerfil, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer perfilUsuarioId = Integer.valueOf(req.getParameter("removePerfil"));
        modeloCadastroUsuarioPerfil.getPerfisUsuario().remove(perfilUsuarioId.intValue());
        return "usuarios/edicao";
    }
}
