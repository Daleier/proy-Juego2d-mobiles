package modelo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import javax.rmi.CORBA.Util;

import controlador.ControladorContact;
import game.B2DVars;
import game.Utiles;

/**
 * Created by dalei on 14/02/2018.
 */

public class Mundo {
    public final float ANCHO_MUNDO;
    public final float ALTO_MUNDO;
    private final int TEMPO_INICIAL_CRONOMETRO = 300;
    private float cronometro;
    private World world;
	private ControladorContact contactListener;
    private PersonajeJugable pj;
    private Array<Zombie> zombiesM;
    private Array<Zombie> zombiesF;

    public Mundo() {
        Utiles.imprimirLog("Mundo","Constructor","Creado objeto mundo");
        this.ANCHO_MUNDO = Gdx.graphics.getWidth();
        this.ALTO_MUNDO = Gdx.graphics.getHeight();
        world = new World(new Vector2(0, -9.8f*2f), true);
        contactListener = new ControladorContact(this);
        world.setContactListener(contactListener);
        // carga layer en variable

        cronometro = TEMPO_INICIAL_CRONOMETRO;
        this.pj = new PersonajeJugable(new Vector2(685,240),new Vector2(52,56.75f),300f, world);
        zombiesF = new Array<Zombie>();
        zombiesM = new Array<Zombie>();
        addZombies();
    }

    private void addZombies() {
        zombiesM.add(new Zombie(new Vector2(5250,630),new Vector2(52,58f),75, world));
        zombiesM.add(new Zombie(new Vector2(5670,630),new Vector2(52,58f),75, world));

        zombiesF.add(new Zombie(new Vector2(1000,160),new Vector2(52,58f),75f, world));
        zombiesF.add(new Zombie(new Vector2(4980,630),new Vector2(52,58f),75f, world));
        zombiesF.add(new Zombie(new Vector2(5500,630),new Vector2(52,58f),-75f, world));
    }

    public PersonajeJugable getPj(){
    	return pj;
	}

	public Array<Zombie> getZombiesM() {
        return zombiesM;
    }

    public Array<Zombie> getZombiesF() {
        return zombiesF;
    }

    public int getCronometro() {
        return (int)cronometro;
    }

    public void setCronometro(float cronometro) {
        this.cronometro = cronometro;
    }

    public void updateCronometro(float delta) {
        cronometro -= delta;
    }

    public World getWorld(){
        return world;
    }

    public ControladorContact getContactListener() {
        return contactListener;
    }



}
