package com.tallerdos.grupo8.ejercicio_1_clima.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

// ¡HEMOS QUITADO @Data!
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClimaRespuesta {

    private List<PronosticoItem> list;

    // --- Getters y Setters Manuales ---
    public List<PronosticoItem> getList() {
        return list;
    }

    public void setList(List<PronosticoItem> list) {
        this.list = list;
    }
}