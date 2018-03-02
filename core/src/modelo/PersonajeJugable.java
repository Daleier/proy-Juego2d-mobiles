package modelo;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import game.B2DVars;

public class PersonajeJugable extends Personaje {

	private Vector2 velocidade;
	private Body body;


	public PersonajeJugable(Vector2 posicion, Vector2 tamano, float velocidade_max,World world) {
		super(posicion, tamano, velocidade_max);
		velocidade = new Vector2(0, 0);
		getRectangulo().setSize(tamano.x / 2);

		//box2d
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(posicion.x/ B2DVars.PIXELS_METER, posicion.y/ B2DVars.PIXELS_METER);
		this.body = world.createBody(bodyDef);

		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox((tamano.x/2)/ B2DVars.PIXELS_METER,(tamano.y/2)/ B2DVars.PIXELS_METER);

		FixtureDef fDef = new FixtureDef();
		fDef.shape = polygonShape;
		fDef.density = 1f;
		// colisiones box2d
		fDef.filter.categoryBits = B2DVars.BIT_JUGADOR;
		fDef.filter.categoryBits = B2DVars.BIT_SUELO;
		body.createFixture(fDef).setUserData("player");
		//foot sensor
		polygonShape.setAsBox((tamano.x /1.95f)/B2DVars.PIXELS_METER,(tamano.y * 0.2f)/B2DVars.PIXELS_METER, new Vector2(0,(-tamano.y*0.03f)/ B2DVars.PIXELS_METER),0);
		fDef.shape = polygonShape;
		fDef.density=1f;
		fDef.filter.categoryBits = B2DVars.BIT_JUGADOR;
		fDef.filter.categoryBits = B2DVars.BIT_SUELO;
		fDef.isSensor= true;
		body.createFixture(fDef).setUserData("foot"); //TODO saltos

		polygonShape.dispose();
	}

	public void jump(){
		body.applyForceToCenter(0, 100, true);
	}

	public void inicializarPJ() {
		setPosicion(100, 20);
		setVelocidadeX(0);
		setVelocidadeY(0);
		setTamano(15, 15);
		getRectangulo().setSize(tamano.x / 2);
	}

	public float getVelocidadeX() {
		return velocidade.x;
	}

	public float getVelocidadeY() {
		return velocidade.y;
	}

	public void setVelocidadeX(float x) {
		velocidade.x = x;
	}

	public void setVelocidadeY(float y) {
		velocidade.y = y;
	}

	@Override
	public void actualizarRectangulo() {
		getRectangulo().x = getPosicion().x + getTamano().x / 4;
		getRectangulo().y = getPosicion().y + getTamano().y / 4;
	}

	public Body getBody() {
		return body;
	}

	@Override
	public void update(float delta) {
		setPosicion(getPosicion().x + velocidade.x * delta, getPosicion().y + velocidade.y * delta);
	}
}

