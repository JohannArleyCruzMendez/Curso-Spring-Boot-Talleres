package com.tallerdos.grupo8.ejercicio_1_clima.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty; // <-- ¡AÑADE ESTA IMPORTACIÓN!
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PronosticoItem {

    private MainData main;
    private List<WeatherData> weather;

    // --- ¡NUEVO CAMPO! ---
    // Usamos @JsonProperty porque el nombre JSON (con guion bajo)
    // es diferente a nuestro nombre Java (camelCase)
    @JsonProperty("dt_txt")
    private String dtTxt;

    // --- Getters y Setters Manuales ---
    public MainData getMain() {
        return main;
    }
    public void setMain(MainData main) {
        this.main = main;
    }

    public List<WeatherData> getWeather() {
        return weather;
    }
    public void setWeather(List<WeatherData> weather) {
        this.weather = weather;
    }

    // --- NUEVOS GETTERS/SETTERS ---
    public String getDtTxt() {
        return dtTxt;
    }
    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }
}
