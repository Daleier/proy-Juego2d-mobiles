package controlador;

import java.util.HashMap;

import game.Utiles;
import modelo.Mundo;
import modelo.Personaje;
import modelo.PersonajeJugable;

/**
 * Created by dalei on 14/02/2018.
 */

public class Controlador {
    private Mundo mundo;
	private PersonajeJugable pj;

	public enum Keys {
		IZQUIERDA, DERECHA,ARRIBA, ABAJO
	}

	HashMap<Keys, Boolean> keys = new HashMap<Controlador.Keys, Boolean>();{
		keys.put(Keys.IZQUIERDA, false);
		keys.put(Keys.DERECHA, false);
		keys.put(Keys.ARRIBA, false);
		keys.put(Keys.ABAJO, false);
	};

    public Controlador(Mundo mundo){
        Utiles.imprimirLog("Controlador","Constructor","Creado objeto controlador");
        this.mundo=mundo;
        this.pj = mundo.getPj();
    }

    public void controlarPJ(float delta) {

	}

    public void update(float delta){
        mundo.updateCronometro(delta);
        controlarPJ(delta);
        procesarEntradas();
    }

	/**
	 * Modifica o estado do mapa de teclas e pon a true
	 * @param tecla: tecla pulsada
	 */
	public void pulsarTecla(Keys tecla){
		keys.put(tecla, true);
	}

	/**
	 * Modifica o estado do mapa de teclas e pon a false
	 * @param tecla: tecla liberada
	 */
	public void liberarTecla(Keys tecla){
		keys.put(tecla, false);
	}

	private void procesarEntradas(){
		if (keys.get(Keys.DERECHA))
			pj.setVelocidadeX(pj.velocidade_max);
		if (keys.get(Keys.IZQUIERDA))
			pj.setVelocidadeX(-pj.velocidade_max);
		if (!(keys.get(Keys.IZQUIERDA)) && (!(keys.get(Keys.DERECHA))))
			pj.setVelocidadeX(0);
		if (keys.get(Keys.ARRIBA))
			pj.setVelocidadeY(pj.velocidade_max);
		if (keys.get(Keys.ABAJO))
			pj.setVelocidadeY(-pj.velocidade_max);
		if (!(keys.get(Keys.ARRIBA)) && (!(keys.get(Keys.ABAJO))))
			pj.setVelocidadeY(0);
	}
}
