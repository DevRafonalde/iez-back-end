package br.com.fiap.on.iez.services;

import br.com.fiap.on.iez.controllers.NumeroController;
import br.com.fiap.on.iez.controllers.PerfilController;
import br.com.fiap.on.iez.controllers.PermissaoController;
import br.com.fiap.on.iez.controllers.UsuarioController;
import br.com.fiap.on.iez.models.entities.orm.*;
import br.com.fiap.on.iez.models.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class InicializacaoService {
    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private PerfilPermissaoRepository perfilPermissaoRepository;

    @Autowired
    private UsuarioPerfilRepository usuarioPerfilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void startSeeder() {
        if (Objects.nonNull(usuarioRepository.findByNomeUser("admin"))) {
            return;
        }

        UsuarioORM usuarioCriado = criarUsuario();
        PerfilORM perfilCriado = criarPerfil();
        List<PermissaoORM> permissoesCriadas = criarPermissoes();
        vincularPerfisPermissoes(perfilCriado, permissoesCriadas);
        vincularUsuariosPerfis(usuarioCriado, perfilCriado);
    }

    private UsuarioORM criarUsuario() {
        UsuarioORM admin = new UsuarioORM();
        admin.setNomeCompleto("Administrador");
        admin.setNomeAmigavel("Administrador");
        admin.setNomeUser("admin");
        admin.setSenhaUser(passwordEncoder.encode("123456"));

        return usuarioRepository.save(admin);
    }

    private PerfilORM criarPerfil() {
        PerfilORM admin = new PerfilORM();
        admin.setNome("Administrador de Sistemas");
        admin.setDescricao("Perfil com todas as permissões do sistema");
        admin.setMnemonico("admin-sistema");

        return perfilRepository.save(admin);
    }

    private List<PermissaoORM> criarPermissoes() {
        List<PermissaoORM> permissoesCriadas = new ArrayList<>();
        List<String> permissoesPerfil = Arrays.stream(PerfilController.class.getDeclaredMethods())
                .map(Method::getName)
                .map(String::toLowerCase)
                .toList();

        List<String> permissoesUsuarios = Arrays.stream(UsuarioController.class.getDeclaredMethods())
                .map(Method::getName)
                .map(String::toLowerCase)
                .toList();

        List<String> permissoesPermissoes = Arrays.stream(PermissaoController.class.getDeclaredMethods())
                .map(Method::getName)
                .map(String::toLowerCase)
                .toList();

        List<String> permissoesNumeros = Arrays.stream(NumeroController.class.getDeclaredMethods())
                .map(Method::getName)
                .map(String::toLowerCase)
                .toList();

        List<String> todasPermissoes = new ArrayList<>();
        todasPermissoes.addAll(permissoesPerfil);
        todasPermissoes.addAll(permissoesUsuarios);
        todasPermissoes.addAll(permissoesPermissoes);
        todasPermissoes.addAll(permissoesNumeros);

        int contadorId = 1;
        for (String nomePermissao : todasPermissoes) {
            PermissaoORM novaPermissao = new PermissaoORM();
            novaPermissao.setId(contadorId++);
            novaPermissao.setNome(nomePermissao);

            PermissaoORM permissaoCriada = permissaoRepository.save(novaPermissao);

            permissoesCriadas.add(permissaoCriada);
        }

        return permissoesCriadas;
    }

    private void vincularPerfisPermissoes(PerfilORM perfilCriado, List<PermissaoORM> permissoesCriadas) {
        for (PermissaoORM permissao : permissoesCriadas) {
            PerfilPermissaoORM perfilPermissao = new PerfilPermissaoORM();
            perfilPermissao.setPerfil(perfilCriado);
            perfilPermissao.setPermissao(permissao);
            perfilPermissao.setDataHora(LocalDateTime.now());

            perfilPermissaoRepository.save(perfilPermissao);
        }
    }

    private void vincularUsuariosPerfis(UsuarioORM usuarioCriado, PerfilORM perfilCriado) {
        UsuarioPerfilORM usuarioPerfil = new UsuarioPerfilORM();
        usuarioPerfil.setUsuario(usuarioCriado);
        usuarioPerfil.setPerfil(perfilCriado);
        usuarioPerfil.setDataHora(LocalDateTime.now());

        usuarioPerfilRepository.save(usuarioPerfil);
    }
}
