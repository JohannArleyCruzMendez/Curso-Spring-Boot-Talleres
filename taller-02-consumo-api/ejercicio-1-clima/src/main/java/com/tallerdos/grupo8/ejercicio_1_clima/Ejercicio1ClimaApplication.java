package com.tallerdos.grupo8.ejercicio_1_clima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Ejercicio1ClimaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ejercicio1ClimaApplication.class, args);}

		// Esto crea un "Bean" (un objeto) de RestTemplate
		// para que Spring pueda inyectarlo en otras clases (como ClimaService)
		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();

	}

}
