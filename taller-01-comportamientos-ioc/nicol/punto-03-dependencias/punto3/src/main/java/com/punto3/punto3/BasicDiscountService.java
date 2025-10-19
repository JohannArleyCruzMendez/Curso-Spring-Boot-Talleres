package com.taller1.demo.Bien;

import org.springframework.stereotype.Service;

@Service("basicDiscount")
//@Primary
public class BasicDiscountService implements DiscountService {

    @Override
    public double aplicarDescuento(double precio) {
        System.out.println("Descuento basico aplicado");
        return precio * 0.95; // Descuento de 5%
    }

}