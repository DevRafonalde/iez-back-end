//package br.com.fiap.on.iez.utils;
//
//import br.com.fiap.on.iez.exceptions.ElementoNaoEncontradoException;
//import br.com.fiap.on.iez.models.entities.orm.UsuarioORM;
//import br.com.fiap.on.iez.models.repositories.UsuarioRepository;
//import br.com.fiap.on.iez.services.JwtService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//@Component
//public class JwtAuthFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtService jwtService;
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        if (request.getServletPath().equals("/usuarios/login")) {
//            filterChain.doFilter(request, response); // pula verificação JWT
//            return;
//        }
//
//        String header = request.getHeader("Authorization");
//
//        if (header == null || !header.startsWith("Bearer ")) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token não fornecido");
//            return;
//        }
//
//        String token = header.replace("Bearer ", "");
//
//        try {
//            int userId = jwtService.validarTokenERetornarId(token);
//            UsuarioORM usuario = usuarioRepository.findById(userId).orElseThrow(() -> new ElementoNaoEncontradoException("Usuário não encontrado"));
//
//            if (!usuario.getAtivo() || usuario.getSenhaAtualizada()) {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido ou usuário desativado");
//                return;
//            }
//
//            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
//                    usuario, null, new ArrayList<>(
//
//            ));
//            SecurityContextHolder.getContext().setAuthentication(auth);
//
//        } catch (Exception e) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
//            return;
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        String path = request.getServletPath();
//        return path.equals("/usuarios/login"); // rota liberada
//    }
//
//}
//
