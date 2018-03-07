package controlador;


import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import modelo.Mundo;

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
            mundo.getPj().addCoin();
        }

        if((fa.getUserData() != null && fa.getUserData().equals("exit")) || (fb.getUserData() != null && fb.getUserData().equals("exit"))){
            System.out.println("SALIDA");
            // TODO fin juego
        }


        if(mundo.getPj().getTiempo_muerte() > mundo.getCronometro()+2 || mundo.getPj().getTiempo_muerte() == 0){
            if((fa.getUserData() != null && fa.getUserData().equals("danger-zone")) || (fb.getUserData() != null && fb.getUserData().equals("danger-zone"))){
                System.out.println("DEAD ZONA PELIGROSA");
                mundo.getPj().muerte(mundo);
            }else if((fa.getUserData() != null && fa.getUserData().equals("enemy")) || (fb.getUserData() != null && fb.getUserData().equals("enemy"))){
                System.out.println("DEAD ENEMIGO");
                mundo.getPj().muerte(mundo);
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
