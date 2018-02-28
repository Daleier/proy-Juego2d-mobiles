package modelo;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import javax.rmi.CORBA.Util;

import controlador.ControladorContact;
import game.B2DVars;
import game.Utiles;

/**
 * Created by dalei on 14/02/2018.
 */

public class Mundo {
    public static final int ANCHO_MUNDO = 480;
    public static final int ALTO_MUNDO = 800;
    private final int TEMPO_INICIAL_CRONOMETRO = 0;
    private float cronometro;
    private World world;
	private ControladorContact contactListener;
    private static PersonajeJugable pj;

    public Mundo() {
        Utiles.imprimirLog("Mundo","Constructor","Creado objeto mundo");
        world = new World(new Vector2(0, -9.8f), true);
        contactListener = new ControladorContact();
        world.setContactListener(contactListener);


        cronometro = TEMPO_INICIAL_CRONOMETRO;
        this.pj = new PersonajeJugable(new Vector2(480,300),new Vector2(32,32),300f, world);

    }


    public PersonajeJugable getPj(){
    	return pj;
	}

    public int getCronometro() {
        return (int)cronometro;
    }

    public void setCronometro(float cronometro) {
        this.cronometro = cronometro;
    }

    public void updateCronometro(float delta) {
        cronometro += delta; //cambiar a -= para cuenta regresiva
    }

    public World getWorld(){
        return world;
    }

    public ControladorContact getContactListener() {
        return contactListener;
    }



}
