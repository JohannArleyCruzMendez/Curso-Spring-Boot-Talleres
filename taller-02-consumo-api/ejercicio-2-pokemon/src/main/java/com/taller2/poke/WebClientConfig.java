package com.taller2.poke;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient pokeApiWebClient() {
        return WebClient.builder()
                .baseUrl("https://pokeapi.co/api/v2") // URL base de la PokeAPI
                .build();
    }
}