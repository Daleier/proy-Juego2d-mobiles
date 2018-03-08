package controlador;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import game.Audio;
import modelo.Mundo;
import modelo.Zombie;
import pantallas.PantallaJuego;

/**
 * Created by dalei on 28/02/2018.
 */

public class ControladorContact implements ContactListener {

    private boolean personajeOnGround;
    private Mundo mundo;

    public ControladorContact(Mundo mundo){
        this.mundo = mundo;
    }
    // 2 fixtures colisionan
    public void beginContact(Contact c) {

        Fixture fa = c.getFixtureA();
        Fixture fb = c.getFixtureB();

        if(fa.getUserData() != null && fa.getUserData().equals("foot")) {
            personajeOnGround = true;
        }
        if(fb.getUserData() != null && fb.getUserData().equals("foot")) {
            personajeOnGround = true;
        }

        if((fa.getUserData() != null && fa.getUserData().equals("coin")) || (fb.getUserData() != null && fb.getUserData().equals("coin"))){
            System.out.println("COIN");
            final Fixture f;
            if(fa.getUserData().equals("coin")){
                f = fa;
                fa.getBody().setUserData("destroy");
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run () {
                        mundo.getWorld().destroyBody(f.getBody());
                    }
                });
            }else if(fb.getUserData().equals("coin")){
                f = fb;
                fb.getBody().setUserData("destroy");
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run () {
                        mundo.getWorld().destroyBody(f.getBody());
                    }
                });
            }
            if(Mundo.isMusicaOn()){
                Audio.soundCoin.play();
            }
            mundo.getPj().addCoin();
        }

        if((fa.getUserData() != null && fa.getUserData().equals("exit")) || (fb.getUserData() != null && fb.getUserData().equals("exit"))){
            System.out.println("SALIDA");
            PantallaJuego.gameOver = true;
        }


        if(mundo.getPj().getTiempo_muerte() > mundo.getCronometro()+2 || mundo.getPj().getTiempo_muerte() == 0){
            if((fa.getUserData() != null && fa.getUserData().equals("danger-zone")) || (fb.getUserData() != null && fb.getUserData().equals("danger-zone"))){
                System.out.println("DEAD ZONA PELIGROSA");
                mundo.getPj().muerte(mundo);
                mundo.getGhost().inicializarGhost();
            }else if((fa.getUserData() != null && fa.getUserData().equals("enemy") && fb.getUserData().equals("player")) ||
                    (fb.getUserData() != null && fb.getUserData().equals("enemy") && fa.getUserData().equals("player"))){
                System.out.println("DEAD ENEMIGO");
                if(Mundo.isMusicaOn()){
                    Audio.soundZombie.play();
                }
                mundo.getGhost().inicializarGhost();
                mundo.getPj().muerte(mundo);
                for(Zombie zombieM : mundo.getZombiesM()){
                    zombieM.inicializarZombie();
                }
                for(Zombie zombieF : mundo.getZombiesF()){
                    zombieF.inicializarZombie();
                }
                Controlador.reinicializarCronometroCambioDireccion();
            }
        }
    }

    // 2 fixtures dejan de colisionar
    public void endContact(Contact c) {
        Fixture fa = c.getFixtureA();
        Fixture fb = c.getFixtureB();

        if(fa.getUserData() != null && fa.getUserData().equals("foot")) {
            personajeOnGround = false;
        }
        if(fb.getUserData() != null && fb.getUserData().equals("foot")) {
            personajeOnGround = false;
        }

    }

    public boolean isPersonajeOnGround() {
        return personajeOnGround;
    }

    public void preSolve(Contact c, Manifold m) {

    }
    public void postSolve(Contact c, ContactImpulse ci) {

    }

}
