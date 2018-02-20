package controlador;

import game.Utiles;
import modelo.Mundo;

/**
 * Created by dalei on 14/02/2018.
 */

public class Controlador {
    private Mundo mundo;

    public Controlador(Mundo mundo){
        Utiles.imprimirLog("Controlador","Constructor","Creado objeto controlador");
        this.mundo=mundo;
    }

    public void update(float delta){
        mundo.updateCronometro(delta);
    }
}
