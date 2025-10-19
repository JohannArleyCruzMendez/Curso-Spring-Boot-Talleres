package taller01.punto1;


import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;



//Lazy // No Crees este bean hasta que alguien lo necesite por primera vez
@Component("ExperimentBean")// Etiqueta para que spring lo gestione y le de un nombre
public class ExperimentService {

    public ExperimentService() {
        // este mensaje indica exatamente cuando se crea este objeto
        System.out.println("Constructor ExperimentService (@Component)llamado.¡bean creado!");


    }

}
