package com.taller1.demo.Bien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    //Sin Primary o Qualifier, Spring no sabria cual inyectar
    /* @Autowired
    private DiscountService discountService;
    */

    //Con @Primary
    /*@Autowired
    //@Qualifier("premiumDiscount")
    private DiscountService discountService;*/ 


    //Con @Qualifier
    /*@Autowired
    @Qualifier("premiumDiscount")
    private DiscountService discountService; */

    // Con inyección opcional
    @Autowired(required = false)
    private DiscountService discountService;

    public double aplicarDescuento(double precio) {
        return discountService.aplicarDescuento(precio);
    }
}