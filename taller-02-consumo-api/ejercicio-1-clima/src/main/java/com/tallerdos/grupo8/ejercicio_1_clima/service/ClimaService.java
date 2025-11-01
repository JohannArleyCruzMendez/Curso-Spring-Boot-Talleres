package com.tallerdos.grupo8.ejercicio_1_clima.service;

import com.tallerdos.grupo8.ejercicio_1_clima.dto.*; // Importa todos tus DTOs
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// Nuevas importaciones para manejar fechas y cálculos
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClimaService {

 @Value("${openweathermap.api.key}")
 private String apiKey;

 private final String API_URL = "https://api.openweathermap.org/data/2.5/forecast";
 private final RestTemplate restTemplate;

 // Definimos un formateador para convertir el String "dtTxt" a un objeto de Fecha/Hora
 private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

 @Autowired
 public ClimaService(RestTemplate restTemplate) {
  this.restTemplate = restTemplate;
 }

 // ¡CAMBIO IMPORTANTE! Ahora devolvemos nuestro DTO personalizado
 public PronosticoResumido getClima(String ciudad) {

  String url = String.format("%s?q=%s&appid=%s&units=metric&lang=es",
          API_URL, ciudad, apiKey);

  // 1. Obtenemos la respuesta completa de la API (igual que antes)
  ClimaRespuesta respuestaAPI = restTemplate.getForObject(url, ClimaRespuesta.class);

  // 2. Creamos el objeto que vamos a devolver
  PronosticoResumido pronosticoResumido = new PronosticoResumido();

  if (respuestaAPI != null && respuestaAPI.getList() != null && !respuestaAPI.getList().isEmpty()) {

   // 3. Procesamos los datos con métodos privados
   pronosticoResumido.setProximas24Horas(procesarProximas24Horas(respuestaAPI.getList()));
   pronosticoResumido.setResumenProximosDias(procesarProximosDias(respuestaAPI.getList()));
  }

  // 4. Devolvemos nuestro objeto de resumen
  return pronosticoResumido;
 }

 /**
  * Calcula el resumen de las próximas 24 horas (los primeros 8 registros de 3h).
  */
 private Resumen24Horas procesarProximas24Horas(List<PronosticoItem> pronosticos) {
  // Tomamos los primeros 8 items (8 * 3 horas = 24 horas)
  List<PronosticoItem> pronosticos24h = pronosticos.stream().limit(8).collect(Collectors.toList());

  Resumen24Horas resumen = new Resumen24Horas();

  // 1. Hora de última actualización (tomamos el primer registro)
  resumen.setHoraUltimaActualizacion(pronosticos24h.get(0).getDtTxt());

  // 2. Temperatura promedio
  double tempPromedio = pronosticos24h.stream()
          .mapToDouble(item -> item.getMain().getTemp())
          .average()
          .orElse(0.0);
  resumen.setTemperaturaPromedio(tempPromedio);

  // 3. Descripción del clima (buscamos la más frecuente)
  String descripcion = pronosticos24h.stream()
          .map(item -> item.getWeather().get(0).getDescription())
          .collect(Collectors.groupingBy(d -> d, Collectors.counting()))
          .entrySet().stream()
          .max(Map.Entry.comparingByValue())
          .map(Map.Entry::getKey)
          .orElse("No disponible");
  resumen.setDescripcionClima(descripcion);

  return resumen;
 }

 /**
  * Calcula el resumen de los próximos 3 días (sin contar hoy).
  */
 private List<ResumenDia> procesarProximosDias(List<PronosticoItem> pronosticos) {
  LocalDate hoy = LocalDate.now();

  // Agrupamos todos los pronósticos por día
  Map<LocalDate, List<PronosticoItem>> pronosticosPorDia = pronosticos.stream()
          .collect(Collectors.groupingBy(
                  item -> LocalDateTime.parse(item.getDtTxt(), FORMATTER).toLocalDate()
          ));

  // Procesamos los próximos 3 días (ignorando hoy)
  return pronosticosPorDia.entrySet().stream()
          .filter(entry -> entry.getKey().isAfter(hoy)) // Ignoramos el día de hoy
          .limit(3) // Solo los próximos 3 días
          .map(entry -> {
           LocalDate fecha = entry.getKey();
           List<PronosticoItem> itemsDelDia = entry.getValue();

           ResumenDia resumenDia = new ResumenDia();
           resumenDia.setFecha(fecha.toString());

           // Calcular Temp. Mínima
           double tempMin = itemsDelDia.stream()
                   .mapToDouble(item -> item.getMain().getTemp())
                   .min().orElse(0.0);
           resumenDia.setTempMinima(tempMin);

           // Calcular Temp. Máxima
           double tempMax = itemsDelDia.stream()
                   .mapToDouble(item -> item.getMain().getTemp())
                   .max().orElse(0.0);
           resumenDia.setTempMaxima(tempMax);

           // Descripción (la más frecuente del día)
           String descripcion = itemsDelDia.stream()
                   .map(item -> item.getWeather().get(0).getDescription())
                   .collect(Collectors.groupingBy(d -> d, Collectors.counting()))
                   .entrySet().stream()
                   .max(Map.Entry.comparingByValue())
                   .map(Map.Entry::getKey)
                   .orElse("No disponible");
           resumenDia.setDescripcion(descripcion);

           return resumenDia;
          })
          .collect(Collectors.toList());
 }
}

