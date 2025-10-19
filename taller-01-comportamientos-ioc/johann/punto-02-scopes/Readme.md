# Taller 2: Gestión de Scopes - Singleton vs. Prototype

---

## Objetivo

El objetivo de este taller fue comprender y diferenciar los scopes de los beans en Spring, específicamente **`singleton`** y **`prototype`**. El ejercicio consistió en demostrar un problema común al inyectar un bean con estado (prototype) en un bean sin estado (singleton) y aplicar la solución correcta para gestionar las instancias de manera adecuada.

---

## Comportamiento de los Scopes

En Spring, el **scope** (o alcance) de un bean define cómo el contenedor IoC gestiona la creación y el ciclo de vida de sus instancias.

### Singleton (El Predeterminado) ⛲
* **Definición:** El contenedor de Spring crea **una única instancia** del bean. Cada vez que se solicita una inyección de este bean en cualquier parte de la aplicación, siempre se devuelve la misma y única instancia.
* **Caso de Uso:** Es ideal para componentes **sin estado (stateless)**, como servicios (`@Service`), repositorios (`@Repository`) o controladores (`@RestController`). Estos beans contienen lógica de negocio reutilizable pero no guardan datos específicos de una transacción o usuario.

### Prototype (Instancias Únicas) 🍾
* **Definición:** El contenedor de Spring crea una **instancia completamente nueva** del bean cada vez que se solicita. Cada inyección o solicitud resulta en un objeto diferente.
* **Caso de Uso:** Es necesario para componentes **con estado (stateful)**, es decir, objetos que necesitan guardar información única para una sesión o tarea específica, como un carrito de compras (`ShoppingCart`), una sesión de usuario, o un asistente de configuración.

---

## El Problema: El Carrito de Compras Compartido

En el primer experimento, se inyectó un `ShoppingCart` (definido como `prototype`) directamente en un `ProductService` (que es `singleton` por defecto).

**Resultado Observado:** Todos los usuarios compartían la misma instancia del carrito de compras. Esto se debe a que la inyección de dependencias ocurre solo **una vez**, en el momento en que se crea el `ProductService`. El `ProductService`, al ser un `singleton`, vive durante toda la ejecución de la aplicación y se aferra a la única instancia de `ShoppingCart` que le fue inyectada al inicio.

---

## La Solución: `ObjectProvider` como Fábrica de Beans

Para corregir este comportamiento, se inyectó un `ObjectProvider<ShoppingCart>` en lugar del `ShoppingCart` directamente.

**Comportamiento Corregido:** El `ObjectProvider` actúa como una fábrica. No inyecta una instancia del carrito, sino la capacidad de solicitar nuevas instancias al contenedor de Spring. Cada vez que el código llama al método `.getObject()`, el `ObjectProvider` le pide al contenedor una nueva instancia del `ShoppingCart`. Como el `ShoppingCart` está definido como `prototype`, el contenedor crea y devuelve un objeto completamente nuevo, asegurando que cada usuario reciba su propio carrito de compras, aislado del resto.

---

## Evidencia de Instancias Distintas

<p>La siguiente captura de pantalla de la consola muestra el resultado de la simulación corregida. Se puede observar que los IDs de instancia (<code>identityHashCode</code>) de los carritos para el "Usuario 1" y el "Usuario 2" son completamente diferentes, lo que demuestra que se crearon dos objetos distintos.</p>

<p align="center">
</p>
<img width="508" height="427" alt="evidencia" src="https://github.com/user-attachments/assets/8d1aebe0-def2-4686-80d1-b8cd86c9d11a" />

<p align="center">
  <em>Evidencia de la ejecución del Punto 2, mostrando la creación de carritos con IDs distintos para cada usuario.</em>
</p>

---

## Conclusión

Este taller demuestra la importancia de seleccionar el scope correcto для cada bean según su propósito. Mientras que `singleton` es la opción más eficiente y común para la lógica sin estado, `prototype` es esencial para manejar objetos con estado. Para inyectar correctamente un bean `prototype` dentro de un `singleton`, es necesario usar un mecanismo como `ObjectProvider` para asegurar que se puedan solicitar instancias nuevas bajo demanda, evitando así el estado compartido no deseado.
