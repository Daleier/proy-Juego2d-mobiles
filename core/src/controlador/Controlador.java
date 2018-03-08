package controlador;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.HashMap;

import game.Utiles;
import modelo.Ghost;
import modelo.Mundo;
import modelo.PersonajeJugable;
import modelo.Zombie;

/**
 * Created by dalei on 14/02/2018.
 */

public class Controlador {
    private Mundo mundo;
	private PersonajeJugable pj;
	private World world;
	private static float cronoCambioDireccion;
	private static float cronoUltimoFantasma;
	private boolean cambioDireccion;

	public enum Keys {
		IZQUIERDA, DERECHA,ARRIBA
	}

	HashMap<Keys, Boolean> keys = new HashMap<Controlador.Keys, Boolean>();{
		keys.put(Keys.IZQUIERDA, false);
		keys.put(Keys.DERECHA, false);
		keys.put(Keys.ARRIBA, false);
	};

    public Controlador(Mundo mundo){
        Utiles.imprimirLog("Controlador","Constructor","Creado objeto controlador");
        this.mundo=mundo;
        this.pj = mundo.getPj();
		world = this.mundo.getWorld();
		cronoCambioDireccion = 0;
		cronoUltimoFantasma = 0;
		cambioDireccion = false;
    }

	public void controlarZombies(){
    	if(cronoCambioDireccion == 0 || cronoCambioDireccion > mundo.getCronometro()+5){
			for (Zombie zombieM: mundo.getZombiesM()){
    			zombieM.cambiarDireccion(cambioDireccion);
			}
			for (Zombie zombieF : mundo.getZombiesF()){
    			zombieF.cambiarDireccion(cambioDireccion);
			}
			cronoCambioDireccion = mundo.getCronometro();
			cambioDireccion = !cambioDireccion;
    	}
	}

	public void controlarFantasma(float delta){
		Ghost ghost = mundo.getGhost();
		ghost.coordenadaDestino.set(mundo.getPj().getBody().getPosition());
		Vector2 direccion = ghost.coordenadaDestino.cpy().sub(ghost.getPosicion());
		ghost.direccion.set(direccion.nor());
		ghost.update(delta);
		if(direccion.x < 0){
			ghost.mirandoFrente = false;
		}else{
			ghost.mirandoFrente = true;
		}
		PersonajeJugable pj = mundo.getPj();
		if(Intersector.overlaps(ghost.getRectangulo(), new Rectangle(pj.getBody().getPosition().x, pj.getBody().getPosition().y, pj.getTamano().x, pj.getTamano().y))
				&& cronoUltimoFantasma > mundo.getCronometro()+5 || cronoUltimoFantasma == 0){
			cronoUltimoFantasma = mundo.getCronometro();
			ghost.inicializarGhost();
			mundo.getPj().muerte(mundo);
		}
	}

	public static void reinicializarCronometroCambioDireccion(){
		cronoCambioDireccion = 0;
	}

    public void update(float delta){
        mundo.updateCronometro(delta);
        world.step(delta,6,2);
		mundo.destroyCoins();
		procesarEntradas();
		controlarZombies();
		controlarFantasma(delta);
    }

	/**
	 * Modifica o estado do mapa de teclas e pon rectangleUpArrow true
	 * @param tecla: tecla pulsada
	 */
	public void pulsarTecla(Keys tecla){
		keys.put(tecla, true);
	}

	/**
	 * Modifica o estado do mapa de teclas e pon rectangleUpArrow false
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
