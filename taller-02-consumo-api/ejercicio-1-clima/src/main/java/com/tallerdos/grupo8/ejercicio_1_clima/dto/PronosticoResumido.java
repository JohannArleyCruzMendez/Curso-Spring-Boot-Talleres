package com.tallerdos.grupo8.ejercicio_1_clima.dto;

import java.util.List;

public class PronosticoResumido {

    private Resumen24Horas proximas24Horas;
    private List<ResumenDia> resumenProximosDias;

    // --- Getters y Setters ---
    public Resumen24Horas getProximas24Horas() {
        return proximas24Horas;
    }
    public void setProximas24Horas(Resumen24Horas proximas24Horas) {
        this.proximas24Horas = proximas24Horas;
    }
    public List<ResumenDia> getResumenProximosDias() {
        return resumenProximosDias;
    }
    public void setResumenProximosDias(List<ResumenDia> resumenProximosDias) {
        this.resumenProximosDias = resumenProximosDias;
    }

}
