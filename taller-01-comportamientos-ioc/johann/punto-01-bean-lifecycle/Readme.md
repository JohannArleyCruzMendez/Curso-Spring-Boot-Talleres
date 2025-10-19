Markdown

# Taller 1: El Laboratorio de Beans - Reporte de Ciclo de Vida

---

## Objetivo

El propósito de este taller fue analizar el ciclo de vida de los beans en el contenedor IoC de Spring Framework, observando las diferencias entre la creación automática y manual, y el impacto de la inicialización perezosa (`@Lazy`).

---

## Tipos de Creación de Beans

En el experimento, se utilizaron las dos formas principales para declarar beans en Spring:

### 1. Autodetección con `@Component`
Esta es una forma de creación **implícita**. Al anotar una clase con `@Component`, le decimos a Spring que la gestione automáticamente. Spring la descubre durante el proceso de escaneo de componentes al arrancar la aplicación.

**Ejemplo (`ExperimentService.java`):**
```java
@Component("experimentBean")
public class ExperimentService {
    public ExperimentService() {
        System.out.println("Constructor de ExperimentService (@Component) llamado.");
    }
}
2. Declaración Manual con @Bean
Esta es una forma de creación explícita. Dentro de una clase anotada con @Configuration, definimos un método que devuelve una instancia de un objeto. Al anotar este método con @Bean, le decimos a Spring que el objeto retornado es un bean que debe ser gestionado por el contenedor.

Ejemplo (AppConfig.java):

Java

@Configuration
public class AppConfig {
    @Bean("manualBean")
    public ManualBean createManualBean() {
        return new ManualBean();
    }
}
Diferencia Principal
La diferencia clave radica en el control y el propósito:

@Component es ideal para que Spring gestione tus propias clases (@Service, @Repository, etc.). La creación es automática y menos configurable.

@Bean te da control total sobre el proceso de instanciación. Es perfecto para crear beans de clases que no son tuyas (ej. librerías externas) o cuando la lógica para crear el objeto es compleja.

Ciclo de Vida de Inicialización
Inicialización Eager (Por Defecto)
Por defecto, Spring adopta una estrategia eager (ansiosa). Esto significa que crea todas las instancias de los beans singleton durante el arranque de la aplicación, antes de que se necesiten.

Observación: Sin usar @Lazy, los mensajes de los constructores de ExperimentService y ManualBean aparecieron en la consola tan pronto como se ejecutó la aplicación.

Inicialización Lazy (Con @Lazy)
La anotación @Lazy cambia este comportamiento a una estrategia lazy (perezosa). Un bean perezoso no se instancia durante el arranque, sino únicamente la primera vez que otro bean lo solicita a través de inyección de dependencias.

Observación: Al poner @Lazy en ExperimentService, solo el constructor de ManualBean se ejecutó al inicio. El constructor de ExperimentService se ejecutó más tarde, justo en el momento en que el CommandLineRunner lo necesitó para ser inyectado.

Conclusión
Este experimento demuestra que Spring ofrece un control granular sobre la creación y el ciclo de vida de los beans. La elección entre @Component y @Bean depende del nivel de control requerido, mientras que @Lazy es una herramienta útil para optimizar los tiempos de arranque y el uso de recursos.
