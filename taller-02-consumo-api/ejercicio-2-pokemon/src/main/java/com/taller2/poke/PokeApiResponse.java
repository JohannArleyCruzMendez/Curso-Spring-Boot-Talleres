package com.taller2.poke;

import java.util.List;

import lombok.Data;

@Data
public class PokeApiResponse {
    private String name;
    private int id;
    private int height; // Altura 
    private int weight; // Peso
    private List<AbilityWrapper> abilities;
    
    // Clase anidada para extraer el nombre de la habilidad
    @Data
    public static class AbilityWrapper {
        private Ability ability;
        
        @Data
        public static class Ability {
            private String name;
        }
    }
}
