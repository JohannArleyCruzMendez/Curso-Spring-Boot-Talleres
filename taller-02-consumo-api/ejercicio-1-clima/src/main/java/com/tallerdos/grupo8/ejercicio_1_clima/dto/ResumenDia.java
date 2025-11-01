package com.tallerdos.grupo8.ejercicio_1_clima.dto;

public class ResumenDia {

        private String fecha;
        private double tempMinima;
        private double tempMaxima;
        private String descripcion;

        // --- Getters y Setters ---
        public String getFecha() {
            return fecha;
        }
        public void setFecha(String fecha) {
            this.fecha = fecha;
        }
        public double getTempMinima() {
            return tempMinima;
        }
        public void setTempMinima(double tempMinima) {
            this.tempMinima = tempMinima;
        }
        public double getTempMaxima() {
            return tempMaxima;
        }
        public void setTempMaxima(double tempMaxima) {
            this.tempMaxima = tempMaxima;
        }
        public String getDescripcion() {
            return descripcion;
        }
        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }
    }

