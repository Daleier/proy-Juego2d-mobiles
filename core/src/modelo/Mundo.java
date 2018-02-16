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
    private final int TEMPO_INICIAL_CRONOMETRO = 0;
    private float cronometro;
    private World world;

    public Mundo() {
        cronometro = TEMPO_INICIAL_CRONOMETRO;
        world = new World(new Vector2(0, -98f), true);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
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
