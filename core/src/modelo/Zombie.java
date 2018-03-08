package modelo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import game.B2DVars;

public class Zombie extends Personaje {

	private Body body;
	private int tiempo_muerte;
	private int vidas_restantes;
	private int coins;

	public Zombie(Vector2 posicion, Vector2 tamano, float velocidade_max, World world) {
		super(posicion, tamano, velocidade_max);
		getRectangulo().setSize(tamano.x / 2);

		//box2d
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(posicion.x, posicion.y);
		this.body = world.createBody(bodyDef);
		this.body.setFixedRotation(true);
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(tamano.x/2,tamano.y/2);
		FixtureDef fDef = new FixtureDef();
		fDef.shape = polygonShape;
		fDef.density = 1f;
		// colisiones box2d
		fDef.filter.categoryBits = B2DVars.BIT_ENEMIGO;
		fDef.filter.maskBits = B2DVars.BIT_SUELO | B2DVars.BIT_JUGADOR;
		body.createFixture(fDef).setUserData("enemy");
		polygonShape.dispose();
		body.setLinearVelocity(velocidade_max, 0);
	}

	public void inicializarZombie() {
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run () {
				body.setLinearVelocity(velocidade_max,0);
				body.setTransform(posicion, 0);
			}
		});
	}

	public Body getBody() {
		return body;
	}

	public void cambiarDireccion(boolean cambio) {
		if(cambio){
			body.setLinearVelocity(velocidade_max, body.getLinearVelocity().y);
		}else{
			body.setLinearVelocity(-velocidade_max, body.getLinearVelocity().y);
		}
	}

	@Override
	public void actualizarRectangulo() {
		getRectangulo().x = getPosicion().x + getTamano().x / 4;
		getRectangulo().y = getPosicion().y + getTamano().y / 4;
	}

}

