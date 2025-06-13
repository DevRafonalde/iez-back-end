package br.com.fiap.on.iez.services;

import br.com.fiap.on.iez.utils.GeradorChaveJwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class JwtService {

    private final String chaveSecreta;

    public JwtService() throws IOException {
        GeradorChaveJwt geradorChaveJwt = new GeradorChaveJwt();
        geradorChaveJwt.aoIniciar();
        this.chaveSecreta = Files.readString(Paths.get("jwt/jwt_secret_key.txt"));
    }

    public int validarTokenERetornarId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(chaveSecreta)
                .parseClaimsJws(token)
                .getBody();

        return claims.get("idUsuario", Integer.class);
    }
}

