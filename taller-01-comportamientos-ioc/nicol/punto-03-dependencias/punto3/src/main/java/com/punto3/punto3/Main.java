package com.taller1.demo.Bien;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("🚀 Aplicación iniciada correctamente");
    }
    @Bean
    public CommandLineRunner run(OrderService orderService) {
        return args -> {
            double precioOriginal = 100.0;
            double precioConDescuento = orderService.aplicarDescuento(precioOriginal);

            System.out.println("Precio original: $" + precioOriginal);
            System.out.println("Precio con descuento: $" + precioConDescuento);
        }; 
    }

}