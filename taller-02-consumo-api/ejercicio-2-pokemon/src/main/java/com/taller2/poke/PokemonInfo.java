package com.taller2.poke;

import java.util.List; // Útil para construir el objeto

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PokemonInfo {
    private String name;
    private int id;
    private double weightKg; // Conversión a kg
    private double heightM; // Conversión a metros
    private List<String> abilities; // Solo una lista simple de nombres de habilidades
}