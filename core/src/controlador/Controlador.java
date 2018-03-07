package controlador;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

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
	private World world;

	public enum Keys {
		IZQUIERDA, DERECHA,ARRIBA, ABAJO, ESPACIO
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
		world = this.mundo.getWorld();
    }

    public void controlarPJ(float delta) {
		//TODO implement
	}

    public void update(float delta){
        mundo.updateCronometro(delta);
        world.step(delta,6,2);
		procesarEntradas();
		controlarPJ(delta);
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
		if (keys.get(Keys.DERECHA)) {
			if (mundo.getContactListener().isPersonajeOnGround()) { //suelo
				pj.getBody().setLinearVelocity(new Vector2(pj.velocidade_max, pj.getBody().getLinearVelocity().y+world.getGravity().y));
			} else {
				if(pj.getBody().getLinearVelocity().y > 0.0f) { // subiendo
					pj.getBody().applyLinearImpulse(new Vector2(5000f, 0),pj.getBody().getWorldCenter(), true);
				}else{ // caida
					pj.getBody().setLinearVelocity(new Vector2(pj.velocidade_max*0.5f, pj.getBody().getLinearVelocity().y+world.getGravity().y));
				}
			}
		}
		if (keys.get(Keys.IZQUIERDA)) {
			if (mundo.getContactListener().isPersonajeOnGround()) { // suelo
				pj.getBody().setLinearVelocity(new Vector2(-pj.velocidade_max, pj.getBody().getLinearVelocity().y+world.getGravity().y));
			}else{
				if(pj.getBody().getLinearVelocity().y > 0.0f) { // subiendo
					pj.getBody().applyLinearImpulse(new Vector2(-5000f, 0),pj.getBody().getWorldCenter(), true);
				}else{ // caida
					pj.getBody().setLinearVelocity(new Vector2(-pj.velocidade_max*0.5f, pj.getBody().getLinearVelocity().y+world.getGravity().y));
				}
			}
		}
		if (!(keys.get(Keys.DERECHA)) && (!(keys.get(Keys.IZQUIERDA)))){
			pj.getBody().setLinearVelocity(new Vector2(0, pj.getBody().getLinearVelocity().y));
		}
		if (keys.get(Keys.ARRIBA)){
			if(mundo.getContactListener().isPersonajeOnGround()) {
				pj.getBody().applyLinearImpulse(new Vector2(pj.getBody().getLinearVelocity().x, 999999999),pj.getBody().getWorldCenter(), true);
			}
		}
	}
}
