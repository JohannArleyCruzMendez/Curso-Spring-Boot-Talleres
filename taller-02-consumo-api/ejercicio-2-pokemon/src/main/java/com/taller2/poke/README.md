Creamos un proyecto llamado Poke que es la abreviatura de pokemon.

1-WebClientConfig
Se crea para configurar y proveer una instancia de WebClient para el consumo de la API.
@Configuration indica que esta clase provee definiciones de beans, lo cual se trabajo en el anterior taller.El método pokeApiWebClient() anotado con @Bean crea y configura un objeto WebClient.
Se establece la URL base de la PokeAPI (https://pokeapi.co/api/v2) para que las llamadas subsiguientes solo necesiten la ruta específica.


2-PokeApiResponse
Usamos esta clase para modelar la estructura de la respuesta JSON que se recibe directamente de la PokeAPI.

El @Data (de Lombok) genera automáticamente getters, setters, toString(), equals() y hashCode().
En esta clase tambien se definen los campos de la API como name, id, height, weight y una lista de abilities. Contiene clases anidadas (AbilityWrapper y Ability) para mapear la estructura de las habilidades que vienen en la respuesta JSON.

3-PokemonInfo
Esta clase se creo para definir el modelo de datos que el controlador devolverá al cliente.
Se usa el @Data (Lombok) para los métodos estándar, el @Builder (Lombok)  para facilitar la construcción del objeto y define los campos con las unidades ya convertidas (weightKg, heightM) y simplifica la lista de habilidades a solo los nombres (abilities).

4-PokemonService
En esta clase consumimos la PokeAPI y transformamos la respuesta a la estructura de PokemonInfo.
Usamos el @Service para definir un componente de servicio de Spring, inyectable. El constructor recibe e inyecta la instancia de WebClient configurada en WebClientConfig.java.

El método getPokemonInfo(String name) usa el webClient para construir una solicitud GET, utilizando la URL base y la ruta específica /pokemon/{name}. El método .retrieve().bodyToMono(PokeApiResponse.class).block() ejecuta la llamada y bloquea el hilo para obtener la respuesta de forma síncrona.
Tambien se debe incluir una verificación básica para manejar el caso de que la respuesta sea nula.

Para poder obtenera la información para el cliente se extrae la lista simple de nombres de habilidades usando un Stream de Java (.map y .collect), despues se crea un objeto PokemonInfo usando el Builder de Lombok.Finaltemte se realizqan las conversiones de los datos de altura y peso.

5-PokemonController
Es la clase encargada de controlar la transacción de datos, expone un endpoint REST para solicitar la información de un Pokémon.

Se usa un @RestController para marcar un controlador REST que combina @Controller y @ResponseBody. Posteriormente se usa @RequestMapping("/api/pokemon") para definir la URL base para todos los métodos del controlador, el constructor inyecta el PokemonService y el método getPokemonByName(@PathVariable String name) define el metodo @GetMapping("/{name}") para defirnir el endpoint completo, define el metodo @PathVariable para estraer el nombre del Pokémon de la URL.
