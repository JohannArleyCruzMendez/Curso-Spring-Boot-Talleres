package com.punto4.punto4;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private InventoryService inventoryService;

    //Constructor inicial que genera el ciclo de dependencia
    /*public OrderService(InventoryService inventoryService) {
        System.out.println("OrderService empiezando");
        this.inventoryService = inventoryService;
    }*/
    public OrderService( ) {
        System.out.println("OrderService empiezando");
    }

    //Se usa inyeccion por setter para romper el ciclo de dependencia, autowiiring lo inyecta automaticamente
    /*@Autowired
    public void setInventoryService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }*/

    public OrderService(@Lazy InventoryService inventoryService) {
        System.out.println("OrderService empiezando");
        this.inventoryService = inventoryService;
    }

    public boolean ventasVerificadas(String productoid){
        System.out.println("OrderService: Verificando ventas para el producto " + productoid);
        return inventoryService.validacionStock(productoid);
    }
}
