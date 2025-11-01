package com.tallerdos.grupo8.ejercicio_1_clima.controller;

// ¡CAMBIO! Importamos el nuevo DTO
import com.tallerdos.grupo8.ejercicio_1_clima.dto.PronosticoResumido;
import com.tallerdos.grupo8.ejercicio_1_clima.service.ClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clima")
public class ClimaController {

    private final ClimaService climaService;

    @Autowired
    public ClimaController(ClimaService climaService) {
        this.climaService = climaService;
    }

    @GetMapping
    // ¡CAMBIO! El método ahora devuelve nuestro resumen
    public PronosticoResumido getClima(@RequestParam String ciudad) {
        return climaService.getClima(ciudad);
    }
}