package taller01.punto1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration // le dice a spring que esta clase contiene recetas de beans


public class AppConfig {

    @Lazy
    @Bean("manualBean") //Le dice a Spring que el objerto que devuelve este metodo es un bean

    public ManualBean createManualBean() {
        return new ManualBean();

    }

}
