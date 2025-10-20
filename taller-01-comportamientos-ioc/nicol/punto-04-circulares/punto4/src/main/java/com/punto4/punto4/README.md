Desarrollo punto 4 primer taller 
1- Se Crean Servicios InventoryService y OrderService ; dentro de InventoryService y OrderService se crean constructores y se crea dentro de InventoryService la función validaciónStock que llama a la función ventasVerificadas que a su vez vuelve a llamar a la función que la invonco. Esto genera un ciclo.

Al ejecutarse genera el error:
The dependencies of some of the beans in the application context form a cycle


Al colocar en el OrderService el setter o el @Lazy la aplicación ejecuta, se observa que se ejecuta el OrderService y termina la aplicación. Los logs que aparecen son:
    2025-10-19T19:15:15.441-05:00  INFO 2652 --- [punto4] [           main] com.punto4.punto4.Punto4Application      : Starting Punto4Application using Java 21.0.8 with PID 2652 (E:\JavaProyects\punto4\target\classes started by MAL in E:\JavaProyects\punto4)
    2025-10-19T19:15:15.444-05:00  INFO 2652 --- [punto4] [           main] com.punto4.punto4.Punto4Application      : No active profile set, falling back to 1 default profile: "default"
    OrderService empiezando
    2025-10-19T19:15:15.876-05:00  INFO 2652 --- [punto4] [           main] com.punto4.punto4.Punto4Application      : Started Punto4Application in 0.859 seconds (process running for 1.18)
    Aplicacion finalizada.

El orden de ejecución de esta aplicación inicia en el main, pasa al OrderService, se detecta un ciclo, vuelve al main y finaliza la ejecución.

public boolean validacionStock(String productoid){
        System.out.println("InventoryService empezando");
        OrderService orderService = orderServiceProvider.getIfAvailable();
        if(orderService != null){
            System.out.println("Llamando a OrderService desde InventoryService para verificar ventas");
            boolean ventasVerificadas = orderService.ventasVerificadas(productoid);
            //Lógica adicional basada en las ventas verificadas
            return ventasVerificadas;
        } else {
            System.out.println("OrderService no está disponible en este momento.");
            return false;
        }

    }
}