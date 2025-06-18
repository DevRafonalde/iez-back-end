package br.com.fiap.on.iez.config;

import br.com.fiap.on.iez.services.InicializacaoService;
import br.com.fiap.on.iez.utils.GeradorChaveJwt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartupRunner {

    @Bean
    public CommandLineRunner init(InicializacaoService inicializacaoService, GeradorChaveJwt geradorChaveJwt) {
        return args -> {
            inicializacaoService.startSeeder();
        };
    }
}
