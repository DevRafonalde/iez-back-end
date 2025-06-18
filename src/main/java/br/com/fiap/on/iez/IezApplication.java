package br.com.fiap.on.iez;

import br.com.fiap.on.iez.utils.GeradorChaveJwt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
@EnableScheduling
public class IezApplication {

	public static void main(String[] args) {
		GeradorChaveJwt geradorChaveJwt = new GeradorChaveJwt();
		try {
			geradorChaveJwt.gerarChaveSecreta();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

        SpringApplication.run(IezApplication.class, args);
	}

	// TODO Fazer método de login
	// TODO Fazer método de criação de token
	// TODO Fazer método de troca de senha

}
