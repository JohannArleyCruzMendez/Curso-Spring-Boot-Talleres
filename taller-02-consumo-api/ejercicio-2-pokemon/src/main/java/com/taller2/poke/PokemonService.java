package com.taller2.poke;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    private final WebClient webClient;

    public PokemonService(WebClient pokeApiWebClient) {
        this.webClient = pokeApiWebClient;
    }

    public PokemonInfo getPokemonInfo(String name) {
        
        // 1. Llamada a la PokeAPI
        PokeApiResponse apiResponse = webClient.get()
                .uri("/pokemon/{name}", name.toLowerCase()) // Parámetro de ruta
                .retrieve()
                .bodyToMono(PokeApiResponse.class)
                .block(); // Bloqueamos para obtener el objeto síncronamente

        // Si la respuesta es nula (ej: Pokémon no encontrado), puedes lanzar una excepción
        if (apiResponse == null) {
            throw new RuntimeException("Pokémon no encontrado: " + name);
        }

        // 2. Transformación al modelo de respuesta personalizado (PokemonInfo)
        
        // Extraer los nombres de las habilidades
        List<String> abilityNames = apiResponse.getAbilities().stream() 
                .map(wrapper -> wrapper.getAbility().getName())
                .collect(Collectors.toList());

        // Construir la respuesta personalizada con conversiones de unidades
        return PokemonInfo.builder()
                .name(apiResponse.getName())
                .id(apiResponse.getId())
                .heightM(apiResponse.getHeight() / 10.0) // Altura está en decímetros (10 dm = 1 m)
                .weightKg(apiResponse.getWeight() / 10.0) // Peso está en hectogramos (10 hg = 1 kg)
                .abilities(abilityNames)
                .build();
    }
}