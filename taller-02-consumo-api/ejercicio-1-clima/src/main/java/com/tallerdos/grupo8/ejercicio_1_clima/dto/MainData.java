package com.tallerdos.grupo8.ejercicio_1_clima.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// ¡HEMOS QUITADO @Data!
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainData {

    private double temp;

    // --- Getters y Setters Manuales ---
    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}