package br.com.fiap.on.iez.controllers;

import br.com.fiap.on.iez.models.entities.dto.PerfilDTO;
import br.com.fiap.on.iez.models.entities.dto.PerfilPermissaoDTO;
import br.com.fiap.on.iez.models.entities.dto.PerfilUsuarioDTO;
import br.com.fiap.on.iez.services.PerfilService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @PostMapping("/cadastrar")
    public ResponseEntity<PerfilPermissaoDTO> cadastrar(PerfilPermissaoDTO perfilPermissaoDTO) {
        PerfilPermissaoDTO perfilCadastrado = perfilService.novoPerfil(perfilPermissaoDTO);

        return new ResponseEntity<>(perfilCadastrado, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PerfilDTO>> listar() {
        List<PerfilDTO> perfis = perfilService.listarTodos();

        return new ResponseEntity<>(perfis, HttpStatus.OK);
    }

    @GetMapping("/listar-usuarios-vinculados/{id}")
    public ResponseEntity<PerfilUsuarioDTO> listarUsuariosVinculados(@PathVariable Integer id) {
        PerfilUsuarioDTO perfilUsuarioDTO = perfilService.listarUsuariosVinculados(id);

        return new ResponseEntity<>(perfilUsuarioDTO, HttpStatus.OK);
    }

//    @GetMapping("/vincular-usuarios-em-lote/{id}")
//    public String vincularUsuariosEmLote(@PathVariable Integer id, ModelMap modelMap) {
//        PerfilUsuarioDTO modeloCadastroPerfilUsuario = perfilService.listarUsuariosVinculados(id);
//        modelMap.addAttribute("modeloCadastroPerfilUsuario", modeloCadastroPerfilUsuario);
//        return "perfis/usuarios-em-lote";
//    }

    @GetMapping("/listar-especifico/{id}")
    public ResponseEntity<PerfilPermissaoDTO> listarEspecifico(@PathVariable Integer id) {
        PerfilPermissaoDTO perfilPermissao = perfilService.listarEspecifico(id);

        return new ResponseEntity<>(perfilPermissao, HttpStatus.OK);
    }

    @GetMapping("/clonar/{id}")
    public ResponseEntity<PerfilPermissaoDTO> clonar(@PathVariable Integer id) {
        PerfilPermissaoDTO perfilPermissaoDTO = perfilService.clonar(id);

        return new ResponseEntity<>(perfilPermissaoDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Boolean> deletar(@PathVariable Integer id) {
        perfilService.deletar(id);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/editar")
    public ResponseEntity<PerfilPermissaoDTO> editar(@Valid PerfilPermissaoDTO perfilPermissaoRecebido) {
        PerfilPermissaoDTO perfilPermissaoAtualizado = perfilService.editar(perfilPermissaoRecebido);

        return new ResponseEntity<>(perfilPermissaoAtualizado, HttpStatus.OK);
    }

//    // Esse método é utilizado via Javascript na página "perfis/usuarios-em-lote" ao invés do próprio Thymeleaf
//    @PostMapping("/vincular-usuarios-em-lote")
//    public ResponseEntity<Integer> vincularUsuariosEmLotePost(@RequestBody ModeloCadastroPerfilUsuarioId modeloCadastroPerfilUsuarioId, RedirectAttributes attributes) {
//        ModeloCadastroPerfilUsuarioId modeloRetorno = perfilService.vincularUsuariosEmLote(modeloCadastroPerfilUsuarioId);
//        attributes.addFlashAttribute("sucesso", "Usuários vinculados com sucesso");
//        return new ResponseEntity<>(modeloRetorno.getPerfil().getId(), HttpStatus.OK);
//    }
}
