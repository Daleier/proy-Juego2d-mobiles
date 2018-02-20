package renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

import game.AssetsJuego;
import game.Utiles;
import modelo.Mundo;
import modelo.PersonajeJugable;

/**
 * Created by dalei on 14/02/2018.
 */

public class Renderer implements InputProcessor{

    private Mundo mundo;
    private boolean debugger=false;
    private SpriteBatch spritebatch;
    private OrthographicCamera camera;
    private ShapeRenderer shaperender;
    private TiledMap mapa;
    private OrthogonalTiledMapRenderer rendererMapa;

    public Renderer(Mundo mundo){
        Utiles.imprimirLog("Renderer","Constructor","Creado objeto renderer");
        this.mundo = mundo;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight()); // TODO cambiar coordenadas camara
        camera.update();
        spritebatch = new SpriteBatch();
        shaperender = new ShapeRenderer();
        mapa = new TmxMapLoader().load("mapas/mapa1.tmx"); //TODO cambiar ruta mapa
		rendererMapa = new OrthogonalTiledMapRenderer(mapa);
        Gdx.input.setInputProcessor(this);
    }

    public void render(float delta){
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.position.set(new Vector2(mundo.getPj().getPosicion().x, mundo.getPj().getPosicion().y),0);
        camera.update();
		rendererMapa.setView(camera);
		rendererMapa.render();
		spritebatch.setProjectionMatrix(camera.combined);
		spritebatch.begin();
		dibujarPj();
		spritebatch.end();

        if(debugger){
            debug();
        }
    }

    public void dibujarPj() {
        PersonajeJugable pj = mundo.getPj();
		spritebatch.draw(AssetsJuego.texturePJ, pj.getPosicion().x, pj.getPosicion().y, pj.getTamano().x, pj.getTamano().y );
    }

    public void debug() {

    }

    public void resize(int width, int height) {
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight()); // TODO cambiar coordenadas camara
        camera.update();
		rendererMapa.setView(camera);
		spritebatch.setProjectionMatrix(camera.combined);
        shaperender.setProjectionMatrix(camera.combined);
    }

    public void dispose() {
        Gdx.input.setInputProcessor(null);
        spritebatch.dispose();
        shaperender.dispose();
        rendererMapa.dispose();
        mapa.dispose();
    }

    public TiledMap getMapa() {
    	return mapa;
	}

	public OrthographicCamera getCamera() {
    	return camera;
	}

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
