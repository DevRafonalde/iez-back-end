package br.com.fiap.on.iez;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IezApplication {

	public static void main(String[] args) {
		SpringApplication.run(IezApplication.class, args);
	}

	// TODO Fazer método de login
	// TODO Fazer método de criação de token
	// TODO Fazer método de troca de senha

}
