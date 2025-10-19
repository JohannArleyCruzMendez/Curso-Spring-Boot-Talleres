
package punto2; // Tu paquete principal para este proyecto

// Importaciones necesarias
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// Esta anotación le dice a Spring que esta es la clase principal
// y que debe escanear el paquete "punto2" en busca de componentes.
@SpringBootApplication
public class Punto2Application {

    // El main solo se encarga de iniciar Spring
    public static void main(String[] args) {
        SpringApplication.run(Punto2Application.class, args);
    }

    // El CommandLineRunner para simular los usuarios
    @Bean
    public CommandLineRunner runnerPunto2(ProductService productService) {

        return args -> {
            System.out.println("--- Simulación Corregida con ObjectProvider ---");

            // Usuario 1 hace su pedido
            productService.processNewOrderForUser("Usuario 1", "Manzanas");


            // Usuario 2 hace su pedido
            productService.processNewOrderForUser("Usuario 2", "Leche");

            System.out.println("\n>> Conclusión: Cada usuario tiene un carrito distinto.");
        };
    }

       // return args -> {
       //     System.out.println("--- Simulación de Carrito de Compras ---");
       //     System.out.println("\n>> Usuario 1 agrega productos:");
       //     productService.addProductToCart("Manzanas");
       //     productService.printCartItems();
       //       System.out.println("\n>> Usuario 2 agrega productos:");
        //       productService.addProductToCart("Leche");
        //    productService.printCartItems();
        //    System.out.println("\n>> Conclusión del Problema <<");
        // };
    //}
}



