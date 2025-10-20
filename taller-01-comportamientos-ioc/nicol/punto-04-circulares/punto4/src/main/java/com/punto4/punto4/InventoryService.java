package com.punto4.punto4;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    //Código inicial que genera el ciclo de dependencia
    /*
    private OrderService orderService;
    public InventoryService(OrderService orderService) {
        System.out.println("InventoryService empezando");
        this.orderService = orderService;
    }

    public boolean validacionStock(String productoid){
        System.out.println("InventoryService empezando");
        return orderService.ventasVerificadas(productoid);
    } */

    //Con ObjectProvider rompemos el ciclo de dependencia ya que no se inyecta directamente la dependencia
    private final ObjectProvider<OrderService> orderServiceProvider;
    
    
    public InventoryService(ObjectProvider<OrderService> orderServiceProvider) {
        System.out.println("InventoryService empezando");
        this.orderServiceProvider = orderServiceProvider;
    }
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
