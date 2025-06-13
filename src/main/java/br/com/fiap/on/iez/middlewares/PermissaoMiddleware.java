package br.com.fiap.on.iez.middlewares;

import br.com.fiap.on.iez.models.entities.dto.PerfilDTO;
import br.com.fiap.on.iez.models.entities.dto.UsuarioPerfilDTO;
import br.com.fiap.on.iez.services.JwtService;
import br.com.fiap.on.iez.services.PerfilService;
import br.com.fiap.on.iez.services.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

@Component
@AllArgsConstructor
public class PermissaoMiddleware implements HandlerInterceptor {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilService perfilService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        token = token.substring(7); // Remove "Bearer "

        int idUsuario;
        try {
            idUsuario = jwtService.validarTokenERetornarId(token);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // Carrega o usuário com perfis e permissões
        UsuarioPerfilDTO usuario = usuarioService.listarEspecifico(idUsuario);
        if (usuario == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // Pega o nome do método/rota requisitado (ex: "/consultarDados" vira "consultardados")
        String uri = request.getRequestURI();
        String metodo = uri.replaceAll("/", "").toLowerCase();

        // Verifica se o usuário tem a permissão
        boolean autorizado = false;
        List<PerfilDTO> perfisUsuario = usuario.getPerfisUsuario();
        for (PerfilDTO perfilDTO : perfisUsuario) {
            autorizado = perfilService.listarEspecifico(perfilDTO.getId()).getPermissoes().stream()
                    .anyMatch(permissao -> permissao.getNome().equalsIgnoreCase(metodo));
        }

        if (!autorizado) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        // Permissão concedida
        return true;
    }
}
