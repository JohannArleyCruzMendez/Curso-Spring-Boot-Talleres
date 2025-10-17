# Taller 01: Comportamientos del Contenedor IoC

Este taller práctico explora en profundidad el contenedor de Inversión de Control (IoC) de Spring Framework. El objetivo es analizar, reproducir y resolver comportamientos complejos relacionados con el ciclo de vida, los scopes y las dependencias de los beans.

---

## 🎯 Objetivos de Aprendizaje

* Comprender cómo se crean, gestionan y destruyen los beans en el contenedor.
* Analizar los efectos de los diferentes scopes (`singleton`, `prototype`).
* Resolver conflictos de inyección y ambigüedades con `@Qualifier` y `@Primary`.
* Diagnosticar y solucionar problemas de dependencias circulares.

---

## 📂 Estructura del Taller

Las soluciones a los cuatro puntos del taller están organizadas en carpetas por participante. Cada participante ha resuelto los siguientes escenarios:

1.  **Punto 1: El Laboratorio del Ciclo de Vida de los Beans**
    * Análisis de la creación de beans con `@Component` y `@Bean`, y el efecto de `@Lazy`.

2.  **Punto 2: Los Clones del Contenedor**
    * Estudio de los scopes `singleton` vs. `prototype` y cómo inyectar beans con estado en beans sin estado.

3.  **Punto 3: La Conspiración de los Qualifiers**
    * Resolución de ambigüedades en la inyección de dependencias.

4.  **Punto 4: El Bucle Infinito**
    * Diagnóstico y solución de dependencias circulares.
