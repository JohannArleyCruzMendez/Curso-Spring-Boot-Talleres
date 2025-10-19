package com.taller1.demo.Bien;

import org.springframework.stereotype.Service;

@Service("premiumDiscount")
public class PremiumDiscountService implements DiscountService {

    @Override
    public double aplicarDescuento(double precio) {
        System.out.println("Descuento premium aplicado");
        return precio * 0.80; // Descuento del 20% 
    }
}
