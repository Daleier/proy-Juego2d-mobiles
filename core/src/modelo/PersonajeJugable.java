package modelo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import controlador.ControladorContact;
import game.B2DVars;

public class PersonajeJugable extends Personaje {

	private Body body;
	private int tiempo_muerte;
	private int vidas_restantes;
	private int coins;

	public PersonajeJugable(Vector2 posicion, Vector2 tamano, float velocidade_max, World world) {
		super(posicion, tamano, velocidade_max);
		getRectangulo().setSize(tamano.x / 2);
		tiempo_muerte = 0;
		vidas_restantes = 3;
		coins = 0;

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
		fDef.filter.categoryBits = B2DVars.BIT_JUGADOR;
		fDef.filter.maskBits = B2DVars.BIT_SUELO | B2DVars.BIT_SALIDA | B2DVars.BIT_ENEMIGO | B2DVars.BIT_COIN | B2DVars.BIT_PELIGRO;
		body.createFixture(fDef).setUserData("player");
		//foot sensor
		polygonShape.setAsBox((tamano.x /2)*0.9f,(tamano.y/2) * 0.2f, new Vector2(0,-tamano.y*0.47f),0);
		fDef.shape = polygonShape;
		fDef.density=1f;
		fDef.filter.categoryBits = B2DVars.BIT_JUGADOR;
		fDef.filter.maskBits = B2DVars.BIT_SUELO;
		fDef.isSensor= true;
		body.createFixture(fDef).setUserData("foot");

		polygonShape.dispose();
	}

	public void inicializarPJ() {
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run () {
				body.setLinearVelocity(0,0);
				body.setTransform(posicion, 0);
			}
		});

	}

	public Body getBody() {
		return body;
	}

	public void muerte(Mundo mundo){
		tiempo_muerte = mundo.getCronometro();
		this.inicializarPJ();
		this.vidas_restantes--;
	}

	public int getTiempo_muerte() {
		return tiempo_muerte;
	}

	public void addCoin(){
		coins++;
	}

	public int getCoins(){
		return coins;
	}

	@Override
	public void actualizarRectangulo() {
		getRectangulo().x = getPosicion().x + getTamano().x / 4;
		getRectangulo().y = getPosicion().y + getTamano().y / 4;
	}

}

