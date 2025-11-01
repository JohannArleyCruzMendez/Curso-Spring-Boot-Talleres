# Taller 02 - Ejercicio 1: API de Clima (OpenWeatherMap)

Este proyecto es la solución al primer ejercicio del Taller 02, enfocado en el consumo de APIs externas con Spring Boot.

## 🎯 Objetivo

Crear una aplicación Spring Boot que consuma la API de pronóstico del clima a 5 días de OpenWeatherMap.

**Requisitos Cumplidos:**
1.  Recibe el nombre de una ciudad como parámetro (`?ciudad=...`).
2.  Consulta la API de pronóstico a 5 días (`/data/2.5/forecast`).
3.  Procesa la respuesta para mostrar un resumen de las **próximas 24 horas**:
    * Temperatura promedio.
    * Descripción general del clima (la más frecuente).
    * Hora de la última actualización.
4.  Procesa la respuesta para mostrar un **resumen de los próximos 3 días**:
    * Fecha.
    * Temperatura mínima y máxima.
    * Descripción del clima (la más frecuente).

---

## 🛠️ Conceptos Aplicados

* **@RestController y @Service:** Separación de responsabilidades entre el controlador (API) y el servicio (lógica de negocio).
* **Consumo de API (GET):** Uso de `RestTemplate` para realizar peticiones `GET` a una API externa.
* **Mapeo de JSON (DTOs):** Creación de un conjunto de clases DTO (Data Transfer Objects) para mapear la respuesta JSON anidada de la API.
    * Uso de `@JsonIgnoreProperties(ignoreUnknown = true)` para ignorar campos no deseados.
    * Uso de `@JsonProperty("dt_txt")` para mapear campos JSON con nombres diferentes a las variables Java.
* **Manejo de Secrets:** Carga segura de la API Key desde `application.properties` usando la anotación `@Value`.
* **Procesamiento de Datos (Java Streams):**
    * Uso de `stream().limit()` para obtener las primeras 24 horas.
    * Cálculo de promedios (`mapToDouble().average()`).
    * Agrupación y conteo (`Collectors.groupingBy()`) para encontrar la descripción del clima más frecuente.
* **Manejo de Fechas y Horas:**
    * Uso de `LocalDateTime`, `LocalDate` y `DateTimeFormatter` para procesar las fechas de la API y agrupar los pronósticos por día.

---

## 🐞 Retos y Depuración: El Misterio del `{}`

Durante el desarrollo, nos enfrentamos a un problema persistente: la API devolvía un estado `200 OK` (éxito), pero el cuerpo de la respuesta en Postman estaba vacío (`{}`).

### Proceso de Diagnóstico

1.  **Error 404 (Inicial):** El primer error fue un `404 Not Found`. Esto se solucionó añadiendo la anotación `@GetMapping` que faltaba en el método del `ClimaController`.
2.  **Respuesta Vacía (`{}`):** El `200 OK` indicaba que el controlador funcionaba, pero el `{}` significaba que el objeto DTO (`ClimaRespuesta`) que devolvía estaba vacío.
3.  **Uso de `Logger`:** Para ver la verdad, modificamos temporalmente el `ClimaService` para que pidiera `String.class` en lugar de `ClimaRespuesta.class` y añadimos un `logger.info()` para imprimir el JSON crudo en la consola.
4.  **Confirmación de Datos:** ¡El log demostró que la API Key era correcta y que la API **sí** estaba devolviendo el JSON completo con la `"list"`!
5.  **Errores de Tipeo:** Revisamos los DTOs y corregimos un error de tipeo (`Maindata` en lugar de `MainData`). Sin embargo, el problema persistía.

### Conclusión y Solución: Lombok

El log probaba que el código era 100% correcto y que los datos llegaban. La única explicación era un fallo en la "magia" de **Lombok**.

Nuestra hipótesis fue que, debido a un problema de la caché del IDE o del build, la anotación `@Data` no estaba generando los métodos `getters` y `setters` (ej. `setList(...)`).

La librería Jackson (que convierte JSON a objetos) necesita estos métodos "setter" para rellenar los DTOs. Como no existían, Jackson fallaba silenciosamente, dejando el objeto vacío.

**Solución:** Eliminamos `@Data` de las 4 clases DTO y escribimos los `getters` y `setters` manualmente. Esto resolvió el problema de inmediato.

---

## 🚀 Cómo Ejecutar

1.  **Obtener API Key:**
    * Regístrate gratis en [OpenWeatherMap](https://openweathermap.org/) y obtén tu API Key.

2.  **Configurar el Proyecto:**
    * Ve al archivo `src/main/resources/application.properties`.
    * Añade la siguiente línea (reemplazando `TU_API_KEY`):
    ```properties
    openweathermap.api.key=TU_API_KEY_SECRETA_AQUI
    ```

3.  **Ejecutar la Aplicación:**
    * Ejecuta la clase principal `Ejercicio1ClimaApplication.java` desde tu IDE.
    * O, desde la terminal, navega a esta carpeta (`ejercicio-1-clima`) y ejecuta:
    ```bash
    ./mvnw spring-boot:run
    ```

4.  **Probar el Endpoint:**
    * Usa Postman o tu navegador para hacer una petición `GET` a:
    ```
    http://localhost:8080/api/clima?ciudad=Bogota
    ```

---

## 🏁 Pantallazo de Salida

<img width="1861" height="1024" alt="Screenshot_2025-10-31_21-55-20" src="https://github.com/user-attachments/assets/b87ae929-2a30-44b1-8ef5-570e07336f3b" />




```json

    "proximas24Horas": {
        "temperaturaPromedio": 13.54625,
        "descripcionClima": "lluvia ligera",
        "horaUltimaActualizacion": "2025-11-01 03:00:00"
    },
    "resumenProximosDias": [
        {
            "fecha": "2025-11-06",
            "tempMinima": 11.31,
            "tempMaxima": 11.31,
            "descripcion": "lluvia ligera"
        },
        {
            "fecha": "2025-11-05",
            "tempMinima": 10.05,
            "tempMaxima": 19.09,
            "descripcion": "lluvia ligera"
        },
        {
            "fecha": "2025-11-04",
            "tempMinima": 8.46,
            "tempMaxima": 18.99,
            "descripcion": "nubes"
        }
    ]
}
