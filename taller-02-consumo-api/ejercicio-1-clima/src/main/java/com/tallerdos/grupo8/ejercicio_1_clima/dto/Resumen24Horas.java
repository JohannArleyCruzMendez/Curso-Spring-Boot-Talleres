package com.tallerdos.grupo8.ejercicio_1_clima.dto;

public class Resumen24Horas {

    private double temperaturaPromedio;
    private String descripcionClima;
    private String horaUltimaActualizacion; // (Será el dt_txt del primer item)

    // --- Getters y Setters ---
    public double getTemperaturaPromedio() {
        return temperaturaPromedio;
    }
    public void setTemperaturaPromedio(double temperaturaPromedio) {
        this.temperaturaPromedio = temperaturaPromedio;
    }
    public String getDescripcionClima() {
        return descripcionClima;
    }
    public void setDescripcionClima(String descripcionClima) {
        this.descripcionClima = descripcionClima;
    }
    public String getHoraUltimaActualizacion() {
        return horaUltimaActualizacion;
    }
    public void setHoraUltimaActualizacion(String horaUltimaActualizacion) {
        this.horaUltimaActualizacion = horaUltimaActualizacion;
    }

}
