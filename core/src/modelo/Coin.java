package modelo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import game.B2DVars;

public class Coin extends Personaje {

	private Body body;

	public Coin(Vector2 posicion, Vector2 tamano, World world) {
		super(posicion, tamano, 0);
		getRectangulo().setSize(tamano.x / 2);

		//box2d
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set(posicion.x, posicion.y);
		this.body = world.createBody(bodyDef);
		this.body.setFixedRotation(true);
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(tamano.x/2,tamano.y/2);
		FixtureDef fDef = new FixtureDef();
		fDef.shape = polygonShape;
		fDef.density = 1f;
		// colisiones box2d
		fDef.filter.categoryBits = B2DVars.BIT_COIN;
		fDef.filter.maskBits = B2DVars.BIT_JUGADOR;
		body.createFixture(fDef).setUserData("coin");
		polygonShape.dispose();
	}


	public Body getBody() {
		return body;
	}

	@Override
	public void actualizarRectangulo() {
		getRectangulo().x = getPosicion().x + getTamano().x / 4;
		getRectangulo().y = getPosicion().y + getTamano().y / 4;
	}

}

