package modelo;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by dalei on 14/02/2018.
 */

public class Mundo {
    public static final int ANCHO_MUNDO = 480;
    public static final int ALTO_MUNDO = 800;
    public static final int PIXELS_METER = 100;
    private final int TEMPO_INICIAL_CRONOMETRO = 0;
    private float cronometro;
    private World world;
	private static PersonajeJugable pj;

    public Mundo() {
        // TODO box2d implementation
        world = new World(new Vector2(0, -98f), true);


        cronometro = TEMPO_INICIAL_CRONOMETRO;
        this.pj = new PersonajeJugable(new Vector2(68,68),new Vector2(32,32),10f, world);


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



}
