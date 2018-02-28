package controlador;


import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by dalei on 28/02/2018.
 */

public class ControladorContact implements ContactListener {

    private boolean personajeOnGround;

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
