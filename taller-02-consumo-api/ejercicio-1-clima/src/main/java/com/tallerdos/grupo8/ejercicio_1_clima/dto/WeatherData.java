package com.tallerdos.grupo8.ejercicio_1_clima.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// ¡HEMOS QUITADO @Data!
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {

    private String description;

    // --- Getters y Setters Manuales ---
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}