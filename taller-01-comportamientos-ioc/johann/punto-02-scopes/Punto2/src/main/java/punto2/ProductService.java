package punto2;


import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // Por defecto, es singleton
public class ProductService {

//Inyectamos el carrito de compras

   // @Autowired

    //private ShoppingCart shoppingCart;

   // public void addProductToCart(String product) {
   //     shoppingCart.addItem(product);
    //}

    //public void printCartItems() {
    //    System.out.println("Items en el carrito: " + shoppingCart.getItems());
    // Imprimimos el código hash para identificar unívocamente la instancia del carrito
    //    System.out.println("ID de la instancia del carrito: " + System.identityHashCode(shoppingCart));


    // object provider
    @Autowired

    //1. En lugar de inyectar el carrito, inyectamos un "proveedor" de carritos.

    private ObjectProvider<ShoppingCart> cartProvider;
    //

    // 2. Creamos un método que simula una nueva orden.
    public void processNewOrderForUser(String user, String product) {
        // 3. ¡Aquí está la magia! Pedimos una instancia FRESCA del carrito.
        ShoppingCart cart = cartProvider.getObject();

        System.out.println("\n>> Procesando orden para " + user);
        System.out.println("ID del nuevo carrito: " + System.identityHashCode(cart));

        cart.addItem(product);
        System.out.println("Items en el carrito de " + user + ": " + cart.getItems());
    }



}







