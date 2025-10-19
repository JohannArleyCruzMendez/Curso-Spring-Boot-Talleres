Descripción del punto
1- Se crea la interfaz con DiscountService con 2 implementaciones 
    - BasicDiscounService: Se creo la implementación del descuento basico con el 5% de descuento
    - PremiumDiscountService: Se creo la implementación del descuento premium con el 20% de descuento.
2- Se ejecuto el Programa y gennero el siguiente error
Field discountService in com.taller1.Punto3.Punto3.OrderService required a single bean, but 2 were found:
        - basicDiscount: defined in file com\taller1\Punto3\Punto3\BasicDiscountService.class
        - premiumDiscount: defined in file \com\taller1\Punto3\Punto3\PremiumDiscountService.class  
Esto se debe a la que no se sabe cual @Service se debe inyectar primero.
Para solucionarlo se debe implementar @Primary o @Quanlifier
3- Al poner en BasicDiscountService @Primary se prioriza este @Sevice y se obtiene el resultado de:
    Descuento basico aplicado
    Precio original: $100.0
    Precio con descuento: $95.0

4- Al poner en OrderService @Qualifier se prioriza este @Service y se obiene como resultado:

Descuento premium aplicado
Precio original: $100.0
Precio con descuento: $80.0

5- Al colocar en OrderService @Autowired(required = false) se genera un null ya que no el @bean que se esta solicitando no se encuentra.
