package taller01;

import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import taller01.punto1.ExperimentService;
import taller01.punto1.ManualBean;

@SpringBootApplication
public class Taller01BeansLifecycleApplication {

    public static void main(String[] args) {
        SpringApplication.run(Taller01BeansLifecycleApplication .class, args);


    }

    @Bean // Este bean se ejecutara al arrancar
    public CommandLineRunner commandLineRunner(
            @Qualifier("ExperimentBean")ExperimentService service1,
            @Qualifier("manualBean")ManualBean service2 )
    {
        return args -> {

            System.out.println("--CommandLineRunner se esta ejecutando");
            System.out.println("Ambos beans ya fueron creados e inyectados en este runner");
            System.out.println("Bean 1(Component)" + service1);
            System.out.println("Bean 2(bean)" + service2);


        };
    }





}
