package modelo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;


import java.util.Iterator;

import controlador.ControladorContact;
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
    private Array<Coin> coins;
    private Ghost ghost;
    private static boolean musicaOn;

    public Mundo() {
        Utiles.imprimirLog("Mundo","Constructor","Creado objeto mundo");
        this.ANCHO_MUNDO = Gdx.graphics.getWidth();
        this.ALTO_MUNDO = Gdx.graphics.getHeight();
        world = new World(new Vector2(0, -9.8f*2f), true);
        contactListener = new ControladorContact(this);
        world.setContactListener(contactListener);
        // carga layer en variable
        Preferences prefs = Gdx.app.getPreferences("preferencias.dat");
        if(!prefs.contains("musicaOn")){
            musicaOn = true;
        }else{
            musicaOn = prefs.getBoolean("musicaOn");
        }

        cronometro = TEMPO_INICIAL_CRONOMETRO;
        this.pj = new PersonajeJugable(new Vector2(685,240),new Vector2(52,56.75f),300f, world);
        this.ghost = new Ghost(new Vector2(105,1400), new Vector2(75,68.75f),100f);
        zombiesF = new Array<Zombie>();
        zombiesM = new Array<Zombie>();
        coins = new Array<Coin>();
        addZombies();
        addCoins();
    }

    private void addZombies() {
        zombiesM.add(new Zombie(new Vector2(5250,630),new Vector2(52,58f),75, world));
        zombiesM.add(new Zombie(new Vector2(5670,630),new Vector2(52,58f),75, world));

        zombiesF.add(new Zombie(new Vector2(1000,160),new Vector2(52,58f),75f, world));
        zombiesF.add(new Zombie(new Vector2(4980,630),new Vector2(52,58f),75f, world));
        zombiesF.add(new Zombie(new Vector2(5500,630),new Vector2(52,58f),-75f, world));
    }

    public void addCoins(){
        coins.add(new Coin(new Vector2(2065,315),new Vector2(32f,32f), world));
        coins.add(new Coin(new Vector2(2485,525),new Vector2(32f,32f), world));
        coins.add(new Coin(new Vector2(1715,735),new Vector2(32f,32f), world));
        coins.add(new Coin(new Vector2(2905,805),new Vector2(32f,32f), world));
        coins.add(new Coin(new Vector2(3395,525),new Vector2(32f,32f), world));
        coins.add(new Coin(new Vector2(3395,525),new Vector2(32f,32f), world));
        coins.add(new Coin(new Vector2(4095,735),new Vector2(32f,32f), world));
        coins.add(new Coin(new Vector2(4865,595),new Vector2(32f,32f), world));
        coins.add(new Coin(new Vector2(5565,595),new Vector2(32f,32f), world));
        coins.add(new Coin(new Vector2(5915,595),new Vector2(32f,32f), world));
        coins.add(new Coin(new Vector2(5215,1575),new Vector2(32f,32f), world));
        coins.add(new Coin(new Vector2(5285,1575),new Vector2(32f,32f), world));
        coins.add(new Coin(new Vector2(5355,1575),new Vector2(32f,32f), world));
        coins.add(new Coin(new Vector2(5425,1575),new Vector2(32f,32f), world));
        coins.add(new Coin(new Vector2(5495,1575),new Vector2(32f,32f), world));
        coins.add(new Coin(new Vector2(6825,805),new Vector2(32f,32f), world));
        coins.add(new Coin(new Vector2(7665,805),new Vector2(32f,32f), world));
        coins.add(new Coin(new Vector2(7385,1505),new Vector2(32f,32f), world));
        coins.add(new Coin(new Vector2(8015,1295),new Vector2(32f,32f), world));
    }

    public void destroyCoins(){
        Iterator<Coin> c = coins.iterator();
        while(c.hasNext()) { //elimina las imagenes que no son de titular
            Coin coin = c.next();
            if(coin.getBody().getUserData() != null){
                if (coin.getBody().getUserData().equals("destroy")) {
                    c.remove();
                }
            }

        }
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

    public Array<Coin> getCoins(){
        return coins;
    }

    public int getCronometro() {
        return (int)cronometro;
    }

    public Ghost getGhost() {
        return ghost;
    }

    public void setCronometro(float cronometro) {
        this.cronometro = cronometro;
    }

    public void updateCronometro(float delta) {
        cronometro -= delta;
    }

    public static boolean isMusicaOn() {
        return musicaOn;
    }

    public static void setMusicaOn(boolean musicaOn) {
        Mundo.musicaOn = musicaOn;
        Preferences prefs = Gdx.app.getPreferences("preferencias.dat");
        prefs.putBoolean("musicaOn", musicaOn);
        prefs.flush();
    }

    public World getWorld(){
        return world;
    }

    public ControladorContact getContactListener() {
        return contactListener;
    }



}
